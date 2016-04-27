package co.ommu.inlisjogja;
/**
 * Created by KurniawanD on 4/27/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.components.OnLoadMoreListener;
import co.ommu.inlisjogja.components.Utility;
import co.ommu.inlisjogja.inlis.adapter.BookSearchAdapter;
import co.ommu.inlisjogja.inlis.model.CatalogSearchModel;
import cz.msebera.android.httpclient.Header;

public class SearchResultActivity extends AppCompatActivity {

    TextView tvKosong;

    String token = "2aff7d8198a8444e9a7909823f91f98d";
    RelativeLayout btnError;

    ArrayList<CatalogSearchModel> arr;
    String nextPager = "";

    int itemCount = 0, pageSize = 0, pageCount = 0, currentPage = 0, nextPage = 0;

    ProgressBar pb;


    String valCatSelect = "title", valWorkSelect = "1", fromActivity = "simple", keyword = "";

    RecyclerView rv;
    BookSearchAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        valCatSelect = getIntent().getStringExtra("category");
        valWorkSelect = getIntent().getStringExtra("worksheet");
        fromActivity = getIntent().getStringExtra("from");
        keyword = getIntent().getStringExtra("keyword");


        rv = (RecyclerView) findViewById(R.id.recycleView);

        tvKosong = (TextView) findViewById(R.id.tv_kosong);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        btnError = (RelativeLayout) findViewById(R.id.rl_error);
        btnError.setVisibility(View.GONE);
        tvKosong.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);

        setList();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getRequest(final boolean isLoadmore, final BookSearchAdapter adap) {
        //private void requestSearch() {

        // gak boleh load jika nextPager.equals("-");

        String urlReq = "";
        if (!isLoadmore) {
            urlReq = "/inlis/api/site/search";
        } else {
            String[] split = nextPager.split(Utility.baseURL);
            urlReq = split[1];
        }

        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("keyword", keyword);
        params.put("category", valCatSelect.toLowerCase().replace(" ", ""));
        params.put("worksheet", valWorkSelect);


        AsynRestClient.post(SearchResultActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    JSONArray ja = response.getJSONArray("data");

                    for (int i = 0; i < ja.length(); i++) {
                        CatalogSearchModel item = new CatalogSearchModel();
                        item.id = ja.getJSONObject(i).getString("id");
                        item.title = ja.getJSONObject(i).getString("title");
                        item.author = ja.getJSONObject(i).getString("author");
                        item.publisher = ja.getJSONObject(i).getString("publisher");
                        item.publish_location = ja.getJSONObject(i).getString("publish_location");
                        item.publish_year = ja.getJSONObject(i).getString("publish_year");
                        item.subject = ja.getJSONObject(i).getString("subject");
                        item.isbn = ja.getJSONObject(i).getString("isbn");
                        item.callnumber = ja.getJSONObject(i).getString("callnumber");
                        item.worksheet = ja.getJSONObject(i).getString("worksheet");
                        arr.add(item);
                    }

                    JSONObject jo = response.getJSONObject("pager");
                    itemCount = Integer.parseInt(jo.getString("itemCount"));
                    pageSize = Integer.parseInt(jo.getString("pageSize"));
                    nextPage = Integer.parseInt(jo.getString("nextPage"));
                    nextPager = response.getString("nextPager");

                    // Log.i("DEBUG search", "_" + ja.toString());


                    if (!isLoadmore) {
                        buildData();
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
            public void onFailure(int statusCode, Header[]  header, Throwable e, JSONObject jo) {
                if (!isLoadmore) {
                    buildError();
                }
            }
        });


    }

    private void buildData() {
        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
        if (arr.size() == 0)
            tvKosong.setVisibility(View.VISIBLE);
        else
            tvKosong.setVisibility(View.GONE);
    }

    private void buildError() {
        tvKosong.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
        btnError.setVisibility(View.VISIBLE);

        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setList();
            }
        });
    }


    private void setList() {

        arr = new ArrayList<>();
        btnError.setVisibility(View.GONE);
        tvKosong.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);


        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookSearchAdapter(SearchResultActivity.this, arr, rv);
        rv.setAdapter(adapter);
        getRequest(false, adapter);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("haint", "Load More");
                arr.add(null);
                adapter.notifyItemInserted(arr.size() - 1);
                //Load more data for reyclerview
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("haint", "Load More 2");
                        //Remove loading item
                        arr.remove(arr.size() - 1);
                        adapter.notifyItemRemoved(arr.size());

                        if (!nextPager.equals("-"))
                            getRequest(true, adapter);
                    }
                }, 1000);
            }
        });


    }

}
