package tech.iosd.gemselections.MainContent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import tech.iosd.gemselections.Astro_RemediesFragment;
import tech.iosd.gemselections.Handicrafts.Handicrafts;
import tech.iosd.gemselections.Ittar.Ittar;
import tech.iosd.gemselections.R;

public class SpiritualItemsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private View khannaHandicraft,KhannaPerfumeries, stoneIdols, yantra, saphatic, japa, kavach, sarva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiritual_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();

        Fragment fr=new SpiritualItemsMainFrag();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(tech.iosd.gemselections.R.id.container_main, fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); // Will show transitioning as fragments change
        fragmentTransaction.commit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStack();

            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.spiritual_items, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        switch (id){

            case tech.iosd.gemselections.R.id.nav_handicrafts:
                //finish();
                startActivity(
                        new Intent(SpiritualItemsActivity.this, Handicrafts.class)
                );
                break;

            case tech.iosd.gemselections.R.id.nav_ittar:
                startActivity(
                        new Intent(SpiritualItemsActivity.this, Ittar.class)
                );
                break;

            case tech.iosd.gemselections.R.id.nav_stoneidols:
                fragment = new MainStoneIdolFragment();
                break;

            case tech.iosd.gemselections.R.id.nav_yantra:
                fragment = new MainYantraFragment();
                break;

            case tech.iosd.gemselections.R.id.nav_saphatic:
                fragment = new MainSphatikFragment();
                break;

            case tech.iosd.gemselections.R.id.nav_japamala:
                fragment = new MainJapaMalaFragment();
                break;

            case tech.iosd.gemselections.R.id.nav_kavach:
                fragment = new MainKavachFragment();
                break;

            case R.id.nav_sarva_mannokaamna_prapti_yugal:
                fragment = new SarvaManokaamnaPraptiYugal();
                break;
        }


        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(tech.iosd.gemselections.R.id.container_main, fragment);
            fragmentTransaction.addToBackStack("Main");
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); // Will show transitioning as fragments change
            fragmentTransaction.commit();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
