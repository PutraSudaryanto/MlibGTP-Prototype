package co.ommu.inlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

import co.ommu.inlis.fragment.IntroFragment;

/**
 * Created by Kurniawan D on 6/24/2016.
 */
public class IntroActivity extends AppCompatActivity {

    CirclePageIndicator indicator;

    public static Boolean statusCheck = false;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        WelcomeAdapter adapter = new WelcomeAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        preferences = getSharedPreferences("preference", Context.MODE_PRIVATE);
    }


    class WelcomeAdapter extends FragmentPagerAdapter {

        public WelcomeAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return new IntroFragment(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
