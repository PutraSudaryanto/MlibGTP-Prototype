package co.ommu.inlisjogja.fragment;


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


import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.components.OnLoadMoreListener;
import co.ommu.inlisjogja.components.Utility;
import co.ommu.inlisjogja.components.CheckConnection;
import co.ommu.inlisjogja.inlis.adapter.TrackAdapter;
import co.ommu.inlisjogja.inlis.model.TrackMemberModel;
import cz.msebera.android.httpclient.Header;

public class TrackFragment extends Fragment {

    public ArrayList<TrackMemberModel> array = new ArrayList<TrackMemberModel>();
    private String name = null;
    String url;
    String itemCount = "0", pageSize = "0", nextPage = "";
    String nextPager = "";

    RecyclerView recycleNotNull;
    TrackAdapter adapter;


    RelativeLayout btnError;
    ProgressBar pb;
    TextView tvKosong;

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

        pb = (ProgressBar) view.findViewById(R.id.progressBar);
        btnError = (RelativeLayout) view.findViewById(R.id.rl_error);
        tvKosong = (TextView) view.findViewById(R.id.tv_kosong);

        Log.i("url umum", url);
        if(CheckConnection.isOnline(getActivity())) {
            setList();
        } else {
            buildError();
        }
        return view;
    }

    private void build() {
        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);

        if (array.size() == 0)
            tvKosong.setVisibility(View.VISIBLE);
        else
            tvKosong.setVisibility(View.GONE);
    }


    private void buildError() {
        pb.setVisibility(View.GONE);
        btnError.setVisibility(View.VISIBLE);
        tvKosong.setVisibility(View.GONE);
        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setList();
            }
        });
    }

    private void setList() {
        array = new ArrayList<>();
        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

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
                        array.remove(array.size() - 1);
                        adapter.notifyItemRemoved(array.size());

                        if (!nextPager.equals("-"))
                            getRequest(true, adapter);
                    }
                }, 1000);
            }
        });

    }

    private void getRequest(final boolean isLoadmore, final TrackAdapter adap) {

        RequestParams params = new RequestParams();
        if (this.name != "popular")
            params.put("type", name);

        String urlReq = "";
        if (!isLoadmore) {
            urlReq = url;
        } else {
            String[] split = nextPager.split(Utility.baseURL);
            urlReq = split[1];
        }


        AsynRestClient.post(getActivity(), urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    JSONArray ja = response.getJSONArray("data");
                    array.addAll(TrackMemberModel.fromJson(ja, false)); // add new items
                    JSONObject jo = response.getJSONObject("pager");
                    itemCount = jo.getString("itemCount");
                    pageSize = jo.getString("pageSize");
                    nextPage = jo.getString("nextPage");

                    nextPager = response.getString("nextPager");
                    if (!isLoadmore) {
                        build();
                    }


                    adap.notifyDataSetChanged();
                    adap.setLoaded();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                if (!isLoadmore) {
                    buildError();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                if (!isLoadmore) {
                    buildError();
                }
            }
        });


    }
}
