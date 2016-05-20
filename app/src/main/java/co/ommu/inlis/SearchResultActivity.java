package co.ommu.inlis;
/**
 * Created by KurniawanD on 4/27/2016.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ommu.inlis.components.AsynRestClient;
import co.ommu.inlis.components.OnLoadMoreListener;
import co.ommu.inlis.components.Utility;
import co.ommu.inlis.inlis.adapter.BookSearchAdapter;
import co.ommu.inlis.inlis.model.CatalogBookModel;
import cz.msebera.android.httpclient.Header;

public class SearchResultActivity extends AppCompatActivity {

    TextView tvKosong;

    String token = "2aff7d8198a8444e9a7909823f91f98d";
    RelativeLayout btnError;

    ArrayList<CatalogBookModel> arr;
    String nextPager = "";

    int itemCount = 0, pageSize = 0, pageCount = 0, currentPage = 0, nextPage = 0;

    ProgressBar pb;


    String valCatSelect = "title", valWorkSelect = "1", fromActivity = "simple", keyword = "";


    String title = "", author = "", publisher = "", publishYear = "", subject = "", callNumber = "",
            bibid = "", ibsn = "";

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


        fromActivity = getIntent().getStringExtra("from");

        if (fromActivity.equals("simple")) {
            valCatSelect = getIntent().getStringExtra("category");
            valWorkSelect = getIntent().getStringExtra("worksheet");
            keyword = getIntent().getStringExtra("keyword");
        } else {
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            publisher = getIntent().getStringExtra("publisher");
            publishYear = getIntent().getStringExtra("publishyear");
            subject = getIntent().getStringExtra("subject");
            callNumber = getIntent().getStringExtra("callnumber");
            bibid = getIntent().getStringExtra("bibid");
            ibsn = getIntent().getStringExtra("ibsn");
        }


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
        RequestParams params = new RequestParams();
        params.put("token", token);

        String urlReq = "";
        if (!isLoadmore) {
            if (fromActivity.equals("simple")) {
                urlReq = "/inlis/api/site/search";
                params.put("keyword", keyword);
                params.put("category", valCatSelect.toLowerCase().replace(" ", ""));
                params.put("worksheet", valWorkSelect);
            } else {
                urlReq = "/inlis/api/site/advanced";
                params.put("title", title);
                params.put("author", author);
                params.put("publisher", publisher);
                params.put("publishyear", publishYear);
                params.put("subject", subject);
                params.put("callnumber", callNumber);
                params.put("bibid", bibid);
                params.put("ibsn", ibsn);

            }
        } else {
            String[] split = nextPager.split(Utility.baseURL);
            urlReq = split[1];
        }


        AsynRestClient.post(SearchResultActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {

                    if (isLoadmore) {
                        arr.remove(arr.size() - 1);
                        adapter.notifyItemRemoved(arr.size());
                    }

                    JSONArray ja = response.getJSONArray("data");

                    arr.addAll(CatalogBookModel.fromJson(ja));

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
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
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
                        //arr.remove(arr.size() - 1);
                        //adapter.notifyItemRemoved(arr.size());

                        if (!nextPager.equals("-"))
                            getRequest(true, adapter);
                    }
                }, 1000);
            }
        });


    }

}
