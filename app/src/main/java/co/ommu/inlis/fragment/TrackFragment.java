package co.ommu.inlis.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ommu.inlis.R;
import co.ommu.inlis.components.AsynRestClient;
import co.ommu.inlis.components.CheckConnection;
import co.ommu.inlis.components.OnLoadMoreListener;
import co.ommu.inlis.components.Utility;
import co.ommu.inlis.inlis.adapter.TrackAdapter;
import co.ommu.inlis.inlis.model.TrackModel;
import cz.msebera.android.httpclient.Header;

public class TrackFragment extends Fragment
{
    public ArrayList<TrackModel> array = new ArrayList<TrackModel>();
    private String name = null;
    String url;
    String itemCount = "0", pageSize = "0", nextPage = "";
    String nextPager = "";
    int isGuest = 0;

    SharedPreferences preferenceAccount;
    RecyclerView recycleNotNull;
    ProgressBar progressBar;
    TextView tvNull;
    RelativeLayout rlError;
    TrackAdapter adapter;

    public TrackFragment(String name) {
        this.name = name;
        if (this.name == null || this.name == "popular")
            url = Utility.inlisLoanPopularPathURL + "/data/JSON";
        else
            url = Utility.inlisCatalogTrackPathURL + "/data/JSON";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track, container, false);

        recycleNotNull = (RecyclerView) view.findViewById(R.id.recycleView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        tvNull = (TextView) view.findViewById(R.id.tv_null);
        rlError = (RelativeLayout) view.findViewById(R.id.rl_error);

        Log.i("url", url);
        if(CheckConnection.isOnline(getActivity()))
            setList();
        else
            buildError();

        return view;
    }

    private void setList() {
        array = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        tvNull.setVisibility(View.GONE);
        rlError.setVisibility(View.GONE);

        recycleNotNull.setHasFixedSize(true);
        recycleNotNull.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TrackAdapter(getActivity(), array, recycleNotNull, false);
        recycleNotNull.setAdapter(adapter);
        getRequest(false, adapter);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("haint", "Load More umum");
                array.add(null);
                adapter.notifyItemInserted(array.size() - 1);
                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("haint", "Load More 2 umum");
                        //Remove loading item
                       // array.remove(array.size() - 1);
                       // adapter.notifyItemRemoved(array.size());

                        if (!nextPager.equals("-"))
                            getRequest(true, adapter);
                        else
                            removeProgres();
                    }
                }, 1000);
            }
        });
    }

    private void getRequest(final boolean isLoadmore, final TrackAdapter adap)
    {
        preferenceAccount = getActivity().getSharedPreferences(Utility.preferenceAccount, Context.MODE_PRIVATE);
        isGuest = preferenceAccount.getInt("isGuest", 0); //0 = belum login, 1=sudah login, 2= skip

        RequestParams params = new RequestParams();
        if (this.name != "popular")
            params.put("type", name);
        params.put("pagesize", 15);
        if(isGuest == 1)
            params.put("token", preferenceAccount.getString("password_token", "-"));

        String urlReq = "";
        if (!isLoadmore)
            urlReq = url;
        else {
            String[] split = nextPager.split(Utility.baseURL);
            urlReq = split[1];
            Log.i("nextPager",urlReq);
        }

        AsynRestClient.post(getActivity(), urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(isLoadmore)
                        removeProgres();

                    JSONArray ja = response.getJSONArray("data");
                    array.addAll(TrackModel.fromJson(ja, false)); // add new items
                    JSONObject jo = response.getJSONObject("pager");
                    itemCount = jo.getString("itemCount");
                    pageSize = jo.getString("pageSize");
                    nextPage = jo.getString("nextPage");

                    nextPager = response.getString("nextPager");
                    if (!isLoadmore)
                        build();

                    adap.notifyDataSetChanged();
                    adap.setLoaded();

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Log.i("infffffooo", "ada parsingan yg salah");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                //Log.i("data", "_" + statusCode);
                if (!isLoadmore)
                    buildError();
                else
                    getRequest(isLoadmore,adap);
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                if (!isLoadmore)
                    buildError();
                else
                    getRequest(isLoadmore,adap);
            }
        });
    }

    private void build() {
        rlError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        if (array.size() == 0)
            tvNull.setVisibility(View.VISIBLE);
        else
            tvNull.setVisibility(View.GONE);
    }

    private void buildError() {
        progressBar.setVisibility(View.GONE);
        tvNull.setVisibility(View.GONE);
        rlError.setVisibility(View.VISIBLE);
        rlError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setList();
            }
        });
    }

    private void removeProgres() {
        array.remove(array.size() - 1);
        adapter.notifyItemRemoved(array.size());
    }
}
