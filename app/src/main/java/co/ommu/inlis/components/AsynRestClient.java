package co.ommu.inlis.components;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

public class AsynRestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.get(context, getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.post(context, getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        String urlPath = Utility.baseURL + relativeUrl;
        Log.i("result", urlPath);
        return urlPath;
    }

    public static void cancelAllRequests(Context context) {
        client.cancelRequests(context, true);
    }

    public static void cancelAllRequests() {
        client.cancelRequests(null, true);
    }

}