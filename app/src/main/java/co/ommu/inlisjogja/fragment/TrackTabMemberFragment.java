package co.ommu.inlisjogja.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.ommu.inlisjogja.R;

public class TrackTabMemberFragment extends Fragment {

    int tabPosition = 0;
    TabLayout tabLayout;

    View view;

    ViewPager viewPager2;
    public static String[] URL_NEXT = {"kosong0", "kosong1", "kosong2"};


    public TrackTabMemberFragment(int pos) {

        tabPosition = pos;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track_tab_memberl, container, false);

        viewPager2 = (ViewPager) view.findViewById(R.id.viewpager);
        if (viewPager2 != null) {
            setupViewPager(viewPager2);
        }

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager2);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        return view;
    }

    private void setupViewPager(final ViewPager viewPager) {
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
                viewPager.getAdapter().notifyDataSetChanged();
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

    class Adapter extends FragmentPagerAdapter {
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

    @Override
    public void onDestroyView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        super.onDestroyView();
    }

}
