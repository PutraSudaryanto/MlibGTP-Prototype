package co.ommu.inlisjogja;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.viewpagerindicator.CirclePageIndicator;

import co.ommu.inlisjogja.components.CustomDialog;
import co.ommu.inlisjogja.fragment.HomeFragment;
import co.ommu.inlisjogja.fragment.TrackTabMemberFragment;
import co.ommu.inlisjogja.fragment.TrackTabFragment;
import co.ommu.inlisjogja.fragment.TrackMemberFragment;
import co.ommu.inlisjogja.fragment.WelcomeFragment;
import  android.widget.Toast;
import co.ommu.inlisjogja.components.LovelyStandardDialog;
import co.ommu.inlisjogja.components.LovelySaveStateHandler;
import co.ommu.inlisjogja.components.LovelyTextInputDialog;


public class WelcomeDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String token = "2aff7d8198a8444e9a7909823f91f98d";
    private DrawerLayout drawer;
    private NavigationView navigationView;

    static String[] URL = {"http://www.wowkeren.com/images/news/00106843.jpg",
            "http://images.cnnindonesia.com/visual/2016/04/01/30a0c1cc-7c9d-4315-9c86-b183cf787d9c_169.jpg",
            "http://www.gulalives.com/gula/wp-content/uploads/2016/04/Maudy-Ayunda-_-brand-ambassador-_-gulalives-_-foto-by-asky.jpg"};
    CirclePageIndicator indicator;
    ViewPager pager;

    RelativeLayout rlPager;
    CollapsingToolbarLayout collapsingToolbar;

    Bundle bunSaved;
    //This can be any numbers. R.id.* were chosen for simplicity of example
   // private static final int ID_STANDARD_DIALOG = R.id.btn_standard_dialog;

    private LovelySaveStateHandler saveStateHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bunSaved = savedInstanceState;
        setContentView(R.layout.activity_welcome_drawer);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        rlPager = (RelativeLayout) findViewById(R.id.rl_pager);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new WelcomeFragment())
                    .commit();
            rlPager.setVisibility(View.VISIBLE);
        }


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        buildPager();

    }

    private void buildPager() {

        pager = (ViewPager) findViewById(R.id.pager);
        PhotoAdapter adap = new PhotoAdapter(getSupportFragmentManager());
        pager.setAdapter(adap);


        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
           getSupportFragmentManager().beginTransaction().replace(R.id.container, new WelcomeFragment()).commit();
        } else if (id == R.id.nav_track) {
           getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackTabMemberFragment(0)).commit();
        } else if (id == R.id.nav_track_favourite) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackMemberFragment("favourites")).commit();
        } else {
            int pos = 0;
            switch (id) {
                case R.id.nav_popular:
                    pos = 0;
                    break;
                case R.id.nav_views:
                    pos = 1;
                    break;
                case R.id.nav_bookmarks:
                    pos = 2;
                    break;
                case R.id.nav_likes:
                    pos = 3;
                    break;
                case R.id.nav_favourites:
                    pos = 4;
                    break;
            }

            // Create new fragment and transaction
           getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackTabFragment(pos)).commit();

        }

        if (id != R.id.nav_home) {
            rlPager.setVisibility(View.GONE);
            collapsingToolbar.setTitleEnabled(false);
        } else {
            rlPager.setVisibility(View.VISIBLE);
            collapsingToolbar.setTitleEnabled(true);
        }

        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public class PhotoAdapter extends FragmentStatePagerAdapter {

        public PhotoAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;

            Bundle args = new Bundle();
            fragment = new PhotoFragment();
            args.putInt(PhotoFragment.ARG_OBJECT, i);

            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 100-object collection.
            return URL.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String title = "";
            return title;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            super.destroyItem(container, position, object);
            pager.removeView(container);
        }

    }


    public static class PhotoFragment extends Fragment {

        public final static String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.sliding_photo, container, false);
            Bundle args = getArguments();

            int no = args.getInt(ARG_OBJECT) + 1;
            int position = args.getInt(ARG_OBJECT);


            ImageView imPhoto = (ImageView) rootView.findViewById(R.id.iv_photo);
            Glide.with(getActivity()).load(URL[position].replace(" ", "%20")).centerCrop().into(imPhoto);

            return rootView;
        }
    }




}
