package com.kewldevs.sathish.nie.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kewldevs.sathish.nie.Fragments.FormDataStore;
import com.kewldevs.sathish.nie.Fragments.FragsAdditionalSymptoms;
import com.kewldevs.sathish.nie.Fragments.FragsAddress;
import com.kewldevs.sathish.nie.Fragments.FragsAdmissionDate;
import com.kewldevs.sathish.nie.Fragments.FragsDOB;
import com.kewldevs.sathish.nie.Fragments.FragsDept;
import com.kewldevs.sathish.nie.Fragments.FragsDistrict;
import com.kewldevs.sathish.nie.Fragments.FragsEnvSample;
import com.kewldevs.sathish.nie.Fragments.FragsGender;
import com.kewldevs.sathish.nie.Fragments.FragsMobNo;
import com.kewldevs.sathish.nie.Fragments.FragsPatientDetails;
import com.kewldevs.sathish.nie.Fragments.FragsSample;
import com.kewldevs.sathish.nie.Fragments.FragsSummary;
import com.kewldevs.sathish.nie.Fragments.FragsSymptoms;
import com.kewldevs.sathish.nie.Fragments.FragsTaluks;
import com.kewldevs.sathish.nie.Fragments.FragsTestDate;
import com.kewldevs.sathish.nie.Others.Helper;
import com.kewldevs.sathish.nie.Others.SectionsPagerAdapter;
import com.kewldevs.sathish.nie.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int currentFrag;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    static FloatingActionButton mFab;
    Random random;
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tlList);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        random = new Random();
        setSupportActionBar(mToolBar);
        //setupNavigationDrawer();
        setupViewPager();
    }



    private void setupViewPager() {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        addFragment(new FragsAdmissionDate(),"Admission");
        addFragment(new FragsDept(),"Department");
        addFragment(new FragsPatientDetails(),"Patient Details");
        addFragment(new FragsDOB(),"Date of Birth");
        addFragment(new FragsGender(),"Gender");
        addFragment(new FragsMobNo(),"Mobile Number");
        addFragment(new FragsDistrict(),"District");
        addFragment(new FragsTaluks(),"Taluks");
        addFragment(new FragsAddress(),"Address");
        addFragment(new FragsSymptoms(),"Symptoms");
        addFragment(new FragsAdditionalSymptoms(),"Additional Symptoms");
        addFragment(new FragsSample(),"Samples");
        addFragment(new FragsTestDate(),"Test Date");
        addFragment(new FragsEnvSample(),"Environment Sample");
        addFragment(new FragsSummary(),"Summary");

        mSectionsPagerAdapter.addFragmentsAndTitles(mFragmentList,mFragmentTitleList);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(15);
        currentFrag = mViewPager.getCurrentItem();
        setTitle(mFragmentTitleList.get(currentFrag));

        if(FormDataStore.isValidated[currentFrag]) thumbsUp();
        else thumbsDown();

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentFrag = position;
                Log.d(Helper.TAG, "Selected: "+currentFrag);
                setTitle(mFragmentTitleList.get(currentFrag));
                if(position<14)
                {
                    Log.d(Helper.TAG, "Boolean:"+ FormDataStore.isValidated[position]);
                    if(FormDataStore.isValidated[position]) thumbsUp();
                    else thumbsDown();
                }else mFab.setImageResource(R.drawable.ic_done_black_24dp);


                //mFab.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255))));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    public boolean onOptionsItemSelected(MenuItem item)
    {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void setTitle(String s)
    {
        getSupportActionBar().setTitle(s);
    }


    private void setupNavigationDrawer() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }


    public static void thumbsUp()
    {
        mFab.setImageResource(R.mipmap.ic_thumbs_up);
    }

    public static void thumbsDown()
    {

        mFab.setImageResource(R.mipmap.ic_thumbs_down);
    }
}
