package co.ommu.inlisjogja;
/**
 * Created by KurniawanD on 4/27/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


import org.json.JSONException;
import org.json.JSONObject;

import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.inlis.model.CatalogBookModel;
import cz.msebera.android.httpclient.Header;

public class BookDetailActivity extends AppCompatActivity {

    String token = "2aff7d8198a8444e9a7909823f91f98d", id_book = "0";
    RelativeLayout btnError;
    ProgressBar pb;
    CatalogBookModel item;
    LinearLayout llContent;

    ImageView ivPhoto;
    TextView tvTitle;


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

                    buildData();

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
        Glide.with(this).load(item.cover.replace(" ", "%20")).centerCrop().into(ivPhoto);


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


}
