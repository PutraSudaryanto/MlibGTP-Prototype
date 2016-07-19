package co.ommu.inlis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Kurniawan D on 6/24/2016.
 */
public class SplashActivity extends Activity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                preferences = getSharedPreferences("preference", Context.MODE_PRIVATE);
                if (preferences.getInt("intro", 0) == 1)
                    startActivity(new Intent(SplashActivity.this, WelcomeDrawerActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, IntroActivity.class));

                // close this activity
                finish();
            }
        }, 3000);
    }

}
