package co.ommu.inlis.components;

/**
 * Created by KurniawanD on 5/24/2016.
 */

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FireBaseAnalytics {

    public static void setRequestAnalytics(Context act, String nameScreen, String titleNews) {
        FirebaseAnalytics mFirebaseAnalytics;
        // Obtain the FirebaseAnalytics instance.

        String upTitle = "";
        if (titleNews.toString().length() > 25)
            upTitle = titleNews.substring(0, 25);
        else
            upTitle = titleNews;

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(act);
        mFirebaseAnalytics.setUserProperty("screen_name", nameScreen);

        if (!titleNews.equals("-")) {
            mFirebaseAnalytics.setUserProperty(nameScreen.replace(" ", "_").toLowerCase(), upTitle);
        }

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, upTitle);
        //bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, upTitle);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, nameScreen.replace(" ", "_").toLowerCase());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


       // Bundle params = new Bundle();
        //params.putString("screen_name", "home");
        //params.putString("title", "home");
        //mFirebaseAnalytics.logEvent("screen_name", params);

    }

}
