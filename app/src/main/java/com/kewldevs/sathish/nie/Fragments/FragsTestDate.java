package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kewldevs.sathish.nie.R;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsTestDate extends Fragment {

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_test_date, container, false);

        //TODO: Dynamically add test dates for samples selected in previous fragment

        return mView;
    }

}
