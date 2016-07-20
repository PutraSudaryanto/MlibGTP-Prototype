package co.ommu.inlis;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity
{
    RelativeLayout btnError;
    ProgressBar pb;

    String title="", url = "";
    Boolean webviewSuccess = true;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(title);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        webView = (WebView) findViewById(R.id.webview);
        btnError = (RelativeLayout) findViewById(R.id.rl_error);
        btnError.setVisibility(View.GONE);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        buildData();

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                if (webviewSuccess) {
                    pb.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                    view.clearCache(true);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Toast.makeText(getActivity(), description,
                // Toast.LENGTH_SHORT).show();
                buildError();
                webviewSuccess = false;
            }


        });

    }


    private void buildData() {
        btnError.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);

        //webView.setVisibility(View.GONE);
        webviewSuccess = true;
        webView.loadUrl(url);
        webView.requestFocus();

    }

    private void buildError() {
        webView.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
        btnError.setVisibility(View.VISIBLE);

        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildData();
            }
        });
    }


}
