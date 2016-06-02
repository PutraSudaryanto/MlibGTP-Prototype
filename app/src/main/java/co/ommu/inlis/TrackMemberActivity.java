package co.ommu.inlis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import co.ommu.inlis.fragment.TrackMemberFragment;

public class TrackMemberActivity extends AppCompatActivity {
    int tabPosition = 0;
    TabLayout tabLayout;

    ViewPager viewPager;
    public static String [] URL_NEXT = {"kosong0","kosong1","kosong2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        if (getIntent().getExtras() != null) {
            tabPosition = getIntent().getExtras().getInt("tab_position");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager();
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            startActivity(new Intent(getBaseContext(), SearchActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TrackMemberFragment("views"), getResources().getString(R.string.action_viewed));
        adapter.addFragment(new TrackMemberFragment("bookmarks"), getResources().getString(R.string.action_bookmarks));
        adapter.addFragment(new TrackMemberFragment("likes"), getResources().getString(R.string.action_likes));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(tabPosition);

        viewPager.setCurrentItem(tabPosition);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setSaveEnabled(true);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabPosition = position;
                viewPager.setCurrentItem(tabPosition);
                viewPager.setSaveEnabled(true);
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> oFragment = new ArrayList<>();
        private final List<String> oFragmentTitle = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            oFragment.add(fragment);
            oFragmentTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return oFragment.get(position);
        }

        @Override
        public int getCount() {
            return oFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return oFragmentTitle.get(position);
        }
    }
}
