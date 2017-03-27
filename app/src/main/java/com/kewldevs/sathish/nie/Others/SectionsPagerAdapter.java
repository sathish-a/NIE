package com.kewldevs.sathish.nie.Others;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sathish on 3/23/17.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    public void addFragmentsAndTitles(List<Fragment> fragments, List<String> titles) {
        mFragmentList = fragments;
        mFragmentTitleList = titles;

    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}