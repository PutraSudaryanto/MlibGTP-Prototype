package co.ommu.inlis.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ommu.inlis.R;
import co.ommu.inlis.components.AsynRestClient;
import co.ommu.inlis.components.Utility;
import co.ommu.inlis.inlis.adapter.BookSearchAdapter;
import co.ommu.inlis.inlis.adapter.RecyclerViewBookAdapter;
import co.ommu.inlis.inlis.model.ArtikelModel;
import co.ommu.inlis.inlis.model.CatalogBookModel;
import co.ommu.inlis.inlis.model.SectionBookModel;
import co.ommu.inlis.inlis.model.SingleBookItemModel;
import cz.msebera.android.httpclient.Header;


public class WelcomeFragment extends Fragment {


    ArrayList<SectionBookModel> allSampleData;
    RecyclerViewBookAdapter adapter;

    RelativeLayout btnError;
    ProgressBar pb;
    RecyclerView recyclerView;

    String url = Utility.bpadBaseURL + "/" + Utility.bpadArticleMainPathURL + "/data/JSON";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);


        allSampleData = new ArrayList<SectionBookModel>();

        //createDummyData();


        pb = (ProgressBar) view.findViewById(R.id.progressBar);
        btnError = (RelativeLayout) view.findViewById(R.id.rl_error);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);


        getRequest();
        return view;
    }


    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            SectionBookModel dm = new SectionBookModel();

            dm.setHeaderTitle("Section " + i);

            ArrayList<SingleBookItemModel> singleItem = new ArrayList<SingleBookItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleBookItemModel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }

    private void buildData() {

        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
        adapter = new RecyclerViewBookAdapter(getActivity(), allSampleData);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);
    }

    private void getRequest() {

        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        allSampleData = new ArrayList<SectionBookModel>();

        RequestParams params = new RequestParams();
        params.put("category", "1");
        params.put("pagesize", "5");


        AsynRestClient.otherPost(getActivity(), url, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // TODO Auto-generated method stub
                try {


                    for (int j = 0; j < response.length(); j++) {


                        ArtikelModel item = new ArtikelModel();
                        item.id = response.getJSONObject(j).getString("id");
                        item.category = response.getJSONObject(j).getString("category");
                        item.category_source = response.getJSONObject(j).getString("category_source");
                        JSONArray ja = response.getJSONObject(j).getJSONArray("data");


                        SectionBookModel dm = new SectionBookModel();

                        dm.setHeaderTitle(item.category);

                        ArrayList<SingleBookItemModel> singleItem = new ArrayList<SingleBookItemModel>();

                        for (int i = 0; i < ja.length(); i++) {

                            ArtikelModel item2 = new ArtikelModel();
                            item2.id = ja.getJSONObject(i).getString("id");
                            item2.category = ja.getJSONObject(i).getString("category");
                            item2.title = ja.getJSONObject(i).getString("title");
                            item2.intro = ja.getJSONObject(i).getString("intro");
                            item2.media_image = ja.getJSONObject(i).getString("media_image");
                            item2.view = ja.getJSONObject(i).getString("view");
                            item2.likes = ja.getJSONObject(i).getString("likes");
                            item2.download = ja.getJSONObject(i).getString("download");
                            item2.published_date = ja.getJSONObject(i).getString("published_date");
                            item2.share = ja.getJSONObject(i).getString("share");

                            singleItem.add(new SingleBookItemModel(item2.title, item2.share));
                        }

                        dm.setAllItemsInSection(singleItem);

                        allSampleData.add(dm);




                    }


                   /* JSONArray ja = response.getJSONArray("data");

                    arr.addAll(CatalogBookModel.fromJson(ja));

                    JSONObject jo = response.getJSONObject("pager");
                    itemCount = Integer.parseInt(jo.getString("itemCount"));
                    pageSize = Integer.parseInt(jo.getString("pageSize"));
                    nextPage = Integer.parseInt(jo.getString("nextPage"));
                    nextPager = response.getString("nextPager");*/

                    buildData();


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

                buildError();

            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {

                buildError();

            }
        });


    }

    private void buildError() {

        pb.setVisibility(View.GONE);
        btnError.setVisibility(View.VISIBLE);

        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequest();
            }
        });
    }


}
