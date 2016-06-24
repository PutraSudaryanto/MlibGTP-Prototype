package co.ommu.inlis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.viewpagerindicator.CirclePageIndicator;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Created by Kurniawan D on 6/24/2016.
 */
public class IntroActivity extends FragmentActivity {

    CirclePageIndicator indicator;

    Boolean statusCheck = false;
    SharedPreferences preferences;

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


    class IntroFragment extends Fragment {

        int position;

        public IntroFragment(int pos) {
            this.position = pos;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.intro_page, container, false);
            LinearLayout linOne = (LinearLayout) view.findViewById(R.id.linOne);
            LinearLayout linTwo = (LinearLayout) view.findViewById(R.id.linTwo);
            CheckBox cbNotif = (CheckBox) view.findViewById(R.id.cb_agree);


            if (position == 2) {
                linOne.setVisibility(View.GONE);
                linTwo.setVisibility(View.VISIBLE);

                cbNotif.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            statusCheck = true;
                        } else {
                            statusCheck = false;
                        }
                    }
                });


            } else {
                linOne.setVisibility(View.VISIBLE);
                linTwo.setVisibility(View.GONE);
            }

            RelativeLayout btnStart = (RelativeLayout) view.findViewById(R.id.btnMulai);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!statusCheck) {
                        Toast.makeText(getApplicationContext(), "Belum di check", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(IntroActivity.this, WelcomeDrawerActivity.class));
                        finish();
                    }

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("intro", 1);
                    editor.commit();


                }
            });
            return view;

        }
    }


}
