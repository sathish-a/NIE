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

public class FragsTaluks extends Fragment {

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.frags_taluks, container, false);
        return mView;
    }

}
