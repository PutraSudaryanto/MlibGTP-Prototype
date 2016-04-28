package co.ommu.inlisjogja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchAdvancedActivity extends AppCompatActivity {

    TextView btnSearchSim;
    EditText edTitle, edAuthor, edPublisher, edPublishYear, edSubject, edCallNumber, edBibid, edIbsn;
    String title = "", author = "", publisher = "", publishYear = "", subject = "", callNumber = "",
            bibid = "", ibsn = "";
    RelativeLayout btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_advanced);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        edTitle = (EditText) findViewById(R.id.input_title);
        edAuthor = (EditText) findViewById(R.id.input_author);
        edPublisher = (EditText) findViewById(R.id.input_publisher);
        edPublishYear = (EditText) findViewById(R.id.input_publish_year);
        edSubject = (EditText) findViewById(R.id.input_subject);
        edCallNumber = (EditText) findViewById(R.id.input_callnumber);
        edBibid = (EditText) findViewById(R.id.input_bibid);
        edIbsn = (EditText) findViewById(R.id.input_isbn);
        btnSearch = (RelativeLayout) findViewById(R.id.rl_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });

        btnSearchSim = (TextView) findViewById(R.id.tv_menu_search);
        btnSearchSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void checkData() {
        if(edTitle.getText().toString().isEmpty() && edAuthor.getText().toString().isEmpty() &&
                edPublisher.getText().toString().isEmpty() &&  edPublishYear.getText().toString().isEmpty() &&
                edSubject.getText().toString().isEmpty() &&  edCallNumber.getText().toString().isEmpty() &&  edBibid.getText().toString().isEmpty() &&
                edIbsn.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Masih belum ada yang terisi",Toast.LENGTH_LONG).show();
            edTitle.requestFocus();
        }
        else {
            if(!edTitle.getText().toString().isEmpty())
                title=edTitle.getText().toString();
            if(!edAuthor.getText().toString().isEmpty())
                author=edAuthor.getText().toString();
            if(!edPublisher.getText().toString().isEmpty())
                publisher=edPublisher.getText().toString();
            if(!edPublishYear.getText().toString().isEmpty())
                publishYear=edPublishYear.getText().toString();
            if(!edSubject.getText().toString().isEmpty())
                subject=edSubject.getText().toString();
            if(!edCallNumber.getText().toString().isEmpty())
                callNumber=edCallNumber.getText().toString();
            if(!edBibid.getText().toString().isEmpty())
                bibid=edBibid.getText().toString();
            if(!edIbsn.getText().toString().isEmpty())
                ibsn=edIbsn.getText().toString();

            startActivity(new Intent(getApplicationContext(), SearchResultActivity.class)
                    .putExtra("title", title)
                    .putExtra("author", author)
                    .putExtra("publisher", publisher)
                    .putExtra("publishyear", publishYear)
                    .putExtra("subject", subject)
                    .putExtra("callnumber", callNumber)
                    .putExtra("bibid", bibid)
                    .putExtra("ibsn", ibsn)
                    .putExtra("from", "advance")
            );

        }

    }


}
