package co.ommu.inlisjogja.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.ommu.inlisjogja.R;
import co.ommu.inlisjogja.components.AsynRestClient;
import co.ommu.inlisjogja.components.Utility;
import co.ommu.inlisjogja.inlis.adapter.TrackAdapter;
import co.ommu.inlisjogja.inlis.model.TrackModel;
import cz.msebera.android.httpclient.Header;

public class TrackTabMemberFragment extends Fragment
{

    int tabPosition = 0;
    TabLayout tabLayout;

    ViewPager viewPager;
    public static String [] URL_NEXT = {"kosong0","kosong1","kosong2"};

    public TrackTabMemberFragment(int pos) {

        tabPosition = pos;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_full, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager();
        }

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        return view;
    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());
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
