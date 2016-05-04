package co.ommu.inlisjogja;
/**
 * Created by KurniawanD on 4/27/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.bumptech.glide.Glide;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.inlis.adapter.CollectionListAdapter;

import co.ommu.inlisjogja.inlis.model.CatalogBookModel;
import co.ommu.inlisjogja.inlis.model.CollectionListModel;
import cz.msebera.android.httpclient.Header;
import co.ommu.inlisjogja.components.DividerItemDecoration;
import android.support.v7.widget.DefaultItemAnimator;
import android.widget.Toast;

public class BookDetailActivity extends AppCompatActivity {

    String token = "2aff7d8198a8444e9a7909823f91f98d", id_book = "0";
    RelativeLayout btnError;
    ProgressBar pb;
    CatalogBookModel item;
    LinearLayout llContent;

    ImageView ivPhoto;
    TextView tvTitle;

    ArrayList<CollectionListModel> arr;
    RecyclerView rv;

    CollectionListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id_book = getIntent().getStringExtra("id");



        pb = (ProgressBar) findViewById(R.id.progressBar);
        btnError = (RelativeLayout) findViewById(R.id.rl_error);

        rv = (RecyclerView) findViewById(R.id.recycleView);


        llContent = (LinearLayout) findViewById(R.id.ll_content);
        ivPhoto= (ImageView) findViewById(R.id.photo);
        tvTitle =(TextView) findViewById(R.id.tv_title);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        getRequest();
    }

    private void getRequest() {


        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        llContent.setVisibility(View.GONE);

        String urlReq = "";

        urlReq = "/inlis/api/site/detail";


        RequestParams params = new RequestParams();
        params.put("token", token);
        params.put("id", id_book);


        AsynRestClient.post(BookDetailActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {


                    item = new CatalogBookModel();
                    item.id = response.getString("id");
                    item.title = response.getString("title");
                    item.author = response.getString("author");
                    item.publisher = response.getString("publisher");
                    item.publish_location = response.getString("publish_location");
                    item.publish_year = response.getString("publish_year");
                    item.subject = response.getString("subject");
                    item.isbn = response.getString("isbn");
                    item.callnumber = response.getString("callnumber");
                    item.worksheet = response.getString("worksheet");
                    item.paging = response.getString("paging");
                    item.sizes = response.getString("sizes");
                    item.description = response.getString("description");
                    item.note = response.getString("note");
                    item.cover = response.getString("cover");

                    getRequestCollection();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                    buildError();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                buildError();

            }
        });


    }

    private void buildData() {
        pb.setVisibility(View.GONE);
        llContent.setVisibility(View.VISIBLE);
        tvTitle.setText(item.title);

        item.cover = "http://2.bp.blogspot.com/-CNNkVbtyfcc/Vq1L-GgL0VI/AAAAAAAAD0Y/hWaxsYx8uw0/s1600/deanda-puteri8.jpg";

        try {
            Glide.with(this).load(item.cover.replace(" ", "%20")).centerCrop().into(ivPhoto);
        } catch (Exception e) {
            Log.i("INFO", "gagal gambar");
        }



        adapter = new CollectionListAdapter(arr);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        /*
        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new ClickListener() {
            public void onClick(View view, int position) {
                CollectionListModel item = arr.get(position);
                Toast.makeText(getApplicationContext(), item.id + " is selected!", Toast.LENGTH_SHORT).show();
            }

            public void onLongClick(View view, int position) {

            }
        }));

*/



        Log.i("infffffooo", "sukses");


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

    private void getRequestCollection() {
        arr = new ArrayList<>();
        RequestParams params = new RequestParams();
        params.put("id",  item.id );

        String urlReq = "";
                urlReq = "/inlis/api/collection/list";


        AsynRestClient.post(BookDetailActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers,JSONArray ja) {
                // TODO Auto-generated method stub
                    arr.addAll(CollectionListModel.fromJson(ja));
                buildData();
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

    /*
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
*/

}
