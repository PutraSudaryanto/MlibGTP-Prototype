package co.ommu.inlisjogja.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ommu.inlisjogja.MainActivity;
import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.components.Utility;
import co.ommu.inlisjogja.inlis.adapter.TrackMemberAdapter;
import co.ommu.inlisjogja.inlis.model.TrackMemberModel;
import cz.msebera.android.httpclient.Header;

public class TrackMemberFragment extends Fragment
{
    public boolean firstTimeLoad = true, loadingMore = false;
    public ArrayList<TrackMemberModel> array = new ArrayList<TrackMemberModel>();
    private String name = null;
    String url;
    String itemCount = "0", pageSize = "0", nextPage = "";
    ProgressDialog dialog;
    RelativeLayout relativeNull;
    RecyclerView recycleNotNull;
    TrackMemberAdapter adapter;

    public TrackMemberFragment(String name) {
        this.name = name;
        if(this.name == null || this.name == "views")
            url = Utility.inlisViewListPathURL + "/data/JSON";
        else if(this.name == "bookmarks")
            url = Utility.inlisBookmarkListPathURL + "/data/JSON";
        else if(this.name == "likes")
            url = Utility.inlisLikeListPathURL + "/data/JSON";
        else if(this.name == "favourites")
            url = Utility.inlisFavouriteListPathURL + "/data/JSON";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_member, container, false);
        relativeNull = (RelativeLayout) view.findViewById(R.id.responseNull);
        relativeNull.setVisibility(View.GONE);
        recycleNotNull = (RecyclerView) view.findViewById(R.id.responseNotNull);
        recycleNotNull.setVisibility(View.GONE);

        Log.i("url", url);
        getData();
        return view;
    }

    private void build() {
        if (firstTimeLoad) {
            if(array.size() == 0)
                relativeNull.setVisibility(View.VISIBLE);
            else
                recycleNotNull.setVisibility(View.VISIBLE);

            if (Integer.parseInt(itemCount) > 20) {
                Log.i("load", "true");
            }
            recycleNotNull.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycleNotNull.setHasFixedSize(true);
            adapter = new TrackMemberAdapter(getActivity(), array);
            recycleNotNull.setAdapter(adapter);

        } else {
            adapter.notifyDataSetChanged();
        }

        firstTimeLoad = false;
    }

    private void getData() {
        if (firstTimeLoad) {
            dialog = ProgressDialog.show(getActivity(), "", "Please wait...", true, true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    // TODO Auto-generated method stub
                    getActivity().onBackPressed();
                    AsynRestClient.cancelAllRequests(getActivity());
                }
            });
        }

        RequestParams params = new RequestParams();
        params.put("token", MainActivity.token);

        AsynRestClient.post(getActivity(), url, params, new JsonHttpResponseHandler() {
            //@Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                // super.onSuccess(response);
                try {
                    JSONArray ja = response.getJSONArray("data");
                    Log.i("DEBUG", ja.toString());
                    array.addAll(TrackMemberModel.fromJson(ja)); // add new items
                    JSONObject jo = response.getJSONObject("pager");
                    itemCount = jo.getString("itemCount");
                    pageSize = jo.getString("pageSize");
                    nextPage = jo.getString("nextPage");
                    url = response.getString("nextPager");
                    Log.i("nextpage", url);
                    build();
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    build();
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    recycleNotNull.setVisibility(View.GONE);
                    relativeNull.setVisibility(View.VISIBLE);
                }
            }

            //@Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, String content) {
                // TODO Auto-generated method stub
                // super.onFailure(statusCode, headers, error, content);
                if (firstTimeLoad) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    loadingMore = false;
                }
            }
        });
    }
}
