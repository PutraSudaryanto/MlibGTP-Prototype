package co.ommu.inlisjogja;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import co.ommu.inlisjogja.fragment.FavouriteFragment;
import co.ommu.inlisjogja.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_views || id == R.id.nav_bookmarks || id == R.id.nav_likes) {
            Intent intent = new Intent(getBaseContext(), TrackActivity.class);
            if (id == R.id.nav_views)
                intent.putExtra("tab_position", 0);
            if (id == R.id.nav_bookmarks)
                intent.putExtra("tab_position", 1);
            if (id == R.id.nav_likes)
                intent.putExtra("tab_position", 2);
            startActivity(intent);
        } else if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        } else if (id == R.id.nav_favourites) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FavouriteFragment()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
