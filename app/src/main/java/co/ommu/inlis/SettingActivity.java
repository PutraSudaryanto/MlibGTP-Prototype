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
import co.ommu.inlis.inlis.adapter.RecyclerViewBookAdapter;
import co.ommu.inlis.inlis.model.CatalogBookModel;
import co.ommu.inlis.inlis.model.SectionBookModel;
import co.ommu.inlis.inlis.model.SingleBookItemModel;
import cz.msebera.android.httpclient.Header;

public class SettingActivity extends AppCompatActivity {


    RecyclerView rv;

    ArrayList<SectionBookModel> allSampleData;
    RecyclerViewBookAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        rv = (RecyclerView) findViewById(R.id.recycleView);
        allSampleData = new ArrayList<SectionBookModel>();


        createDummyData();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void createDummyData() {


        for (int i = 0; i < 2; i++) {

            SectionBookModel dm = new SectionBookModel();
            ArrayList<SingleBookItemModel> singleItem = new ArrayList<SingleBookItemModel>();

            if (i == 0) {
                dm.setHeaderTitle("Grtahama Pustaka");
                singleItem.add(new SingleBookItemModel("Address :  Jl. Janti Banguntapan Bantul, Telepon : 0274 - 4536236 / 4536234 /4536233", "URL", 1));
                singleItem.add(new SingleBookItemModel("Location", "-7.799126, 110.406932", 2));
            } else {
                dm.setHeaderTitle("Mlib GTP");
                singleItem.add(new SingleBookItemModel("Report a Problem", "bpad_diy@yahoo.com", 3));
                singleItem.add(new SingleBookItemModel("Rate Us", "URL", 4));
            }


            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
        buildData();
    }

    private void buildData() {


        adapter = new RecyclerViewBookAdapter(getApplicationContext(), allSampleData);

        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        rv.setAdapter(adapter);
    }


}
