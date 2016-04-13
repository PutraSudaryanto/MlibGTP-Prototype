package co.ommu.inlisjogja.fragment;

import android.app.ProgressDialog;
import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.components.Utility;
import co.ommu.inlisjogja.inlis.adapter.PopularAdapter;
import co.ommu.inlisjogja.inlis.model.PopularModel;
import cz.msebera.android.httpclient.Header;

public class PopularFragment extends Fragment
{
    public boolean firstTimeLoad = true, loadingMore = false;
    public ArrayList<PopularModel> array = new ArrayList<PopularModel>();
    String url;
    String itemCount = "0", pageSize = "0", nextPage = "";
    ProgressDialog dialog;
    RelativeLayout relativeNull;
    RecyclerView recycleNotNull;
    PopularAdapter adapter;
    View footerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_content, container, false);
        recycleNotNull = (RecyclerView) view.findViewById(R.id.responseNotNull);
        relativeNull = (RelativeLayout) view.findViewById(R.id.responseNull);
        footerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.include_footer, null, false);

        url = Utility.inlisLoanPopularPathURL + "/data/JSON";
        Log.i("url", url);
        getResult();
        return view;
    }

    private void build() {
        if (firstTimeLoad) {
            if (Integer.parseInt(itemCount) > 20) {
                Log.i("load", "true");
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recycleNotNull.setLayoutManager(layoutManager);
            recycleNotNull.setHasFixedSize(true);

            adapter = new PopularAdapter(getActivity());
            recycleNotNull.setAdapter(adapter);

        } else {
            adapter.notifyDataSetChanged();
        }

        firstTimeLoad = false;
    }

    private void getResult() {
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

        AsynRestClient.post(getActivity(), url, null, new JsonHttpResponseHandler() {
            //@Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                // super.onSuccess(response);
                try {
                    JSONArray ja = response.getJSONArray("data");
                    Log.i("result", ja.toString());
                    for (int i = 0; i < ja.length(); i++) {
                        PopularModel item = new PopularModel();
                        item.catalog_id = ja.getJSONObject(i).getString("catalog_id");
                        item.loans = ja.getJSONObject(i).getString("loans");
                        item.title = ja.getJSONObject(i).getString("title");
                        item.author = ja.getJSONObject(i).getString("author");
                        item.publish_year = ja.getJSONObject(i).getString("publish_year");
                        item.publisher = ja.getJSONObject(i).getString("publisher");
                        item.publish_location = ja.getJSONObject(i).getString("publish_location");
                        item.subject = ja.getJSONObject(i).getString("subject");
                        array.add(item);
                    }
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
