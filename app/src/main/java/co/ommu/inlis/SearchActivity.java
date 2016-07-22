package co.ommu.inlis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import co.ommu.inlis.components.AsynRestClient;
import co.ommu.inlis.components.OnLoadMoreListener;
import co.ommu.inlis.components.Utility;
import co.ommu.inlis.inlis.adapter.BookSearchAdapter;
import co.ommu.inlis.inlis.model.CatalogBookModel;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    TextView btnSearchAdv, tvKosong;

    String token = "2aff7d8198a8444e9a7909823f91f98d";

    EditText edKeyword;
    RelativeLayout btnSearch, btnError;
    Spinner spWork, spCategory;

    ArrayList<CatalogBookModel> arr;
    String nextPager = "";
    Boolean firstTime = true;

    int itemCount = 0, pageSize = 0, pageCount = 0, currentPage = 0, nextPage = 0;

    ProgressBar pb;

    String[] nameCategory = {"Title", "Author", "Publisher", "Publish Year", "Subject", "Call Number", "Bibid", "Isbn"};

    String[] nameWorkSheet = {"Monograf", "Berkas Komputer", "Film", "Tebitan Berkala", "Bahan Kartografis", "Bahan Grafis",
            "Rekaman Video", "Musik", "Bahan Campuran", "Rekaman Suara", "Bentuk Mikro",
            "Manuskrip", "Serial", "Braille",};
    String[] idWorkSheet = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",};

    String valCatSelect = "title", valWorkSelect = "1";

    ArrayAdapter<String> adapSpinCategory = null, adapSpinWork = null;
    RecyclerView rv;
    BookSearchAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        edKeyword = (EditText) findViewById(R.id.input_keyword);
        btnSearch = (RelativeLayout) findViewById(R.id.rl_search);

        rv = (RecyclerView) findViewById(R.id.recycleView);

        spCategory = (Spinner) findViewById(R.id.sp_category);
        spWork = (Spinner) findViewById(R.id.sp_worksheet);


        adapSpinCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameCategory);

        spCategory.setAdapter(adapSpinCategory);
        // spCategory.setSelection(0);
        adapSpinCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategory.getSelectedItemPosition();

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                valCatSelect = nameCategory[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                valCatSelect = nameCategory[0];
            }

        });

        adapSpinWork = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameWorkSheet);

        spWork.setAdapter(adapSpinWork);
        // spCategory.setSelection(0);
        adapSpinWork.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spWork.getSelectedItemPosition();

        spWork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                valWorkSelect = idWorkSheet[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                valWorkSelect = idWorkSheet[0];
            }

        });

        tvKosong = (TextView) findViewById(R.id.tv_null);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        btnError = (RelativeLayout) findViewById(R.id.rl_error);
        btnError.setVisibility(View.GONE);
        tvKosong.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edKeyword.getText().toString().isEmpty()) {
                    edKeyword.requestFocus();
                    Toast.makeText(getApplicationContext(), "Keyword is empty", Toast.LENGTH_LONG).show();
                } else {
                    /*
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
*/
                    // setList();

                    startActivity(new Intent(getApplicationContext(), SearchResultActivity.class)
                            .putExtra("category", valCatSelect.toLowerCase().replace(" ", ""))
                            .putExtra("worksheet", valWorkSelect)
                            .putExtra("from", "simple")
                            .putExtra("keyword", edKeyword.getText().toString())
                    );
                }


            }
        });


        btnSearchAdv = (TextView) findViewById(R.id.tv_menu_search);
        btnSearchAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getBaseContext(), SearchAdvancedActivity.class));

            }
        });


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
            urlReq = "/inlis/api/search/list";
        } else {
            String[] split = nextPager.split(Utility.baseURL);
            urlReq = split[1];
        }

        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("keyword", edKeyword.getText().toString());
        params.put("category", valCatSelect.toLowerCase().replace(" ", ""));
        params.put("worksheet", valWorkSelect);


        AsynRestClient.post(SearchActivity.this, urlReq, params, new JsonHttpResponseHandler() {

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
        adapter = new BookSearchAdapter(SearchActivity.this, arr, rv);
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
                       // arr.remove(arr.size() - 1);
                       // adapter.notifyItemRemoved(arr.size());

                        if (!nextPager.equals("-"))
                            getRequest(true, adapter);
                    }
                }, 1000);
            }
        });


    }

}
