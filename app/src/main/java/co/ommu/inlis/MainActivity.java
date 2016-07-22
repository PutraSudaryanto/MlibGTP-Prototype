package co.ommu.inlis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.ommu.inlis.components.AsynRestClient;
import co.ommu.inlis.components.LovelySaveStateHandler;
import co.ommu.inlis.components.LovelyTextInputChangePasswordDialog;
import co.ommu.inlis.components.Utility;
import co.ommu.inlis.fragment.TrackFragment;
import co.ommu.inlis.fragment.TrackMemberFragment;
import co.ommu.inlis.fragment.ArticleMainFragment;
import co.ommu.inlis.inlis.model.ArtikelModel;
import co.ommu.inlis.inlis.model.SectionBookModel;
import co.ommu.inlis.inlis.model.SingleBookItemModel;
import cz.msebera.android.httpclient.Header;
import co.ommu.inlis.inlis.model.BannerModel;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    @BindView(R.id.toolbar) Toolbar toolbar;

    Bundle bunSaved;
    public static String InstanceIDToken = "";

    /**
     * Intro Variable
     */
    public static final String PREF_KEY_FIRST_START = "PREF_KEY_FIRST_START";
    public static final int REQUEST_CODE_INTRO = 1;

    /**
     * Drawer Navigation
     */
    private DrawerLayout drawer;
    private NavigationView navigationView;

    // Drawer Menu
    int isGuest = 0;
    String displayName = "", memberNumber = "", passwordToken="", oauthToken="";
    SharedPreferences preferenceAccount, preferenceIntro;
    TextView tvDispayname, tvMemberNumber;
    Button btnLogin;

    /**
     * Banner
     */
    boolean bannerIsNull = true;
    static ArrayList<BannerModel> arrBanner = new ArrayList<>();
    CollapsingToolbarLayout collapsingToolbar;
    RelativeLayout rlBannerPager;
    ViewPager vpBannerPager;
    CirclePageIndicator indicator;

    SharedPreferences.Editor editorLogin, editorIntro;

    //misc
    private LovelySaveStateHandler saveStateHandler;
    String oldPass = "", newPass = "", conPass = "", success = "", message = "", token = "";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bunSaved = savedInstanceState;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Toolbar
        setSupportActionBar(toolbar);

        /**
         * Drawer Navigation
         */
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Drawer Menu
        navigationView = (NavigationView) findViewById(R.id.nv_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        tvDispayname = (TextView) header.findViewById(R.id.tvDispayname);
        tvMemberNumber = (TextView) header.findViewById(R.id.tvMemberNumber);
        btnLogin = (Button) header.findViewById(R.id.action_login);

        /**
         * Banner
         */
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        rlBannerPager = (RelativeLayout) findViewById(R.id.rl_banner_pager);

        /**
         * Article
         */
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new ArticleMainFragment())
                    .commit();
            if(!bannerIsNull)
                rlBannerPager.setVisibility(View.VISIBLE);
        }

        loadPreferenceAccount();
        getBannerRequest();
        //dialogChangePassword();

        InstanceIDToken = FirebaseInstanceId.getInstance().getToken();

        //Intro Condition
        preferenceIntro = getSharedPreferences(Utility.preferenceIntro, Context.MODE_PRIVATE);
        if (preferenceIntro.getBoolean(PREF_KEY_FIRST_START, true) == true) {
            Intent intent = new Intent(this, SplashIntroActivity.class);
            startActivityForResult(intent, REQUEST_CODE_INTRO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INTRO) {
            if (resultCode == RESULT_OK) {
                editorIntro = preferenceIntro.edit();
                editorIntro.putBoolean(PREF_KEY_FIRST_START, false);
                editorIntro.commit();
            }
        }
    }

    public void loadPreferenceAccount() {
        preferenceAccount = getSharedPreferences(Utility.preferenceAccount, Context.MODE_PRIVATE);

        isGuest = preferenceAccount.getInt("isGuest", 0); //0 = belum login, 1=sudah login, 2= skip
        switch (isGuest) {
            case 1:
                displayName = preferenceAccount.getString("displayname", "");
                memberNumber = preferenceAccount.getString("member_number", "");
                passwordToken= preferenceAccount.getString("password_token", "");
                oauthToken= preferenceAccount.getString("oauth_token", "");
                tvDispayname.setText(displayName);
                tvMemberNumber.setText(memberNumber);
                btnLogin.setVisibility(View.GONE);
                break;
            default:
                tvMemberNumber.setVisibility(View.GONE);
                break;
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                finish();
            }
        });
    }

    /**
     * Banner
     */
    private void getBannerRequest() {
        rlBannerPager.setVisibility(View.GONE);
        arrBanner = new ArrayList<>();

        String url = Utility.bpadBaseURL + "/" + Utility.bpadBannerPathURL + "/data/JSON";
        RequestParams params = new RequestParams();
        params.put("category", "mlibgtp_main");

        AsynRestClient.otherPost(this, url, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int j = 0; j < response.length(); j++) {
                        BannerModel item = new BannerModel();
                        item.id = response.getJSONObject(j).getString("id");
                        item.title = response.getJSONObject(j).getString("title");
                        item.image = response.getJSONObject(j).getString("image");
                        item.url = response.getJSONObject(j).getString("url");

                        arrBanner.add(item);
                    }

                    if(response.length() > 0) {
                        bannerIsNull = false;
                        rlBannerPager.setVisibility(View.VISIBLE);
                    } else
                        collapsingToolbar.setTitleEnabled(false);

                    buildBannerPager();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                //Log.i("data", "_" + statusCode);
                // buildError();
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                //  buildError();
            }
        });
    }

    private void buildBannerPager() {
        vpBannerPager = (ViewPager) findViewById(R.id.vp_banner_pager);
        BannerAdapter adap = new BannerAdapter(getSupportFragmentManager());
        vpBannerPager.setAdapter(adap);

        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vpBannerPager);
    }

    public class BannerAdapter extends FragmentStatePagerAdapter {
        public BannerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;

            Bundle args = new Bundle();
            fragment = new BannerFragment();
            args.putInt(BannerFragment.ARG_OBJECT, i);

            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 100-object collection.
            return arrBanner.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            return title;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            super.destroyItem(container, position, object);
            vpBannerPager.removeView(container);
        }
    }

    public static class BannerFragment extends Fragment
    {
        public final static String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.item_sliding_banner, container, false);
            Bundle args = getArguments();

            int no = args.getInt(ARG_OBJECT) + 1;
            final int position = args.getInt(ARG_OBJECT);


            ImageView imPhoto = (ImageView) rootView.findViewById(R.id.iv_photo);

            //Glide.with(getActivity()).load(URL[position].replace(" ", "%20")).centerCrop().into(imPhoto);

            Glide.with(getActivity()).load(arrBanner.get(position).image.replace(" ", "%20")).centerCrop().into(imPhoto);

            if (!arrBanner.get(position).url.equals("-")) {
                imPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), WebViewActivity.class)
                                .putExtra("title", arrBanner.get(position).title)
                                .putExtra("url", arrBanner.get(position).url)
                        );
                    }
                });
            }

            return rootView;
        }
    }

    private void dialogChangePassword() {
        new LovelyTextInputChangePasswordDialog(this, R.style.EditTextTintTheme)
                .setTopColorRes(R.color.darkDeepOrange)
                .setTitle(R.string.text_input_title)
                .setMessage(R.string.text_input_message)
                .setIcon(R.mipmap.ic_launcher)
                //.setInstanceStateHandler(ID_TEXT_INPUT_DIALOG, saveStateHandler)

                .setInputFilter(R.string.text_input_error_message, new LovelyTextInputChangePasswordDialog.TextFilter() {
                            @Override
                            public boolean check(String email) {
                                return email.matches("\\w+");
                            }
                        }, new LovelyTextInputChangePasswordDialog.TextFilter() {
                            @Override
                            public boolean check(String name) {
                                return name.matches("\\w+");
                            }
                        },
                        new LovelyTextInputChangePasswordDialog.TextFilter() {
                            @Override
                            public boolean check(String member) {
                                return member.matches("\\w+");
                            }
                        })
                .setConfirmButton(android.R.string.ok, new LovelyTextInputChangePasswordDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String email, String name, String member) {
                        Toast.makeText(getApplicationContext(), email + "_" + name + "_" + member, Toast.LENGTH_SHORT).show();
                        oldPass = email;
                        newPass = member;
                        conPass = name;
                        getRequestChangePassword();
                    }
                })
                .setSavedInstanceState(bunSaved)
                .show();
    }

    private void getRequestChangePassword() {

        String urlReq = "/inlis/api/user/changepassword";
        RequestParams params = new RequestParams();

        params.put("token", token);
        params.put("password", oldPass);
        params.put("newpassword", newPass);
        params.put("confirmpassword", conPass);


        pd = ProgressDialog.show(this, "", "Please wait...", true, true);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                // TODO Auto-generated method stub
                AsynRestClient.cancelAllRequests(getApplicationContext());
            }
        });


        AsynRestClient.post(MainActivity.this, urlReq, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // TODO Auto-generated method stub
                try {
                    success = response.getString("success");
                    message = response.getString("message");
                    if (success.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Sukses Perubahan Password", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                    pd.dismiss();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.i("infffffooo", "ada parsingan yg salah");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, String res, Throwable e) {
                // TODO Auto-generated method stub
                Log.i("data", "_" + statusCode);
                pd.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e, JSONObject jo) {
                pd.dismiss();
            }
        });
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

        if (id == R.id.nav_home) { // menu.Basic
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArticleMainFragment()).commit();
        } else if (id == R.id.nav_tracks) {
            // jadi activity
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackTabMemberFragment(0)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArticleMainFragment()).commit();
            startActivity(new Intent(MainActivity.this, TrackMemberActivity.class));

        } else if (id == R.id.nav_track_favourite) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackMemberFragment("favourites")).commit();

        } else if (id == R.id.nav_popular) { // menu.GlobalTracks
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackFragment("popular")).commit();
        } else if (id == R.id.nav_views) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackFragment("view")).commit();
        } else if (id == R.id.nav_bookmarks) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackFragment("bookmark")).commit();
        } else if (id == R.id.nav_likes) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackFragment("like")).commit();
        } else if (id == R.id.nav_favourites) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TrackFragment("favourite")).commit();

        } else if (id == R.id.nav_settings) { // menu.Setting
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        } else if (id == R.id.nav_helps) {
            return true;
        }

        if (id == R.id.nav_home || id == R.id.nav_tracks) {
            if(!bannerIsNull) {
                rlBannerPager.setVisibility(View.VISIBLE);
                collapsingToolbar.setTitleEnabled(true);
            } else
                collapsingToolbar.setTitleEnabled(false);

        } else {
            rlBannerPager.setVisibility(View.GONE);
            collapsingToolbar.setTitleEnabled(false);
        }

        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
