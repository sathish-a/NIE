package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.R;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsEnvSample extends Fragment {

    View mView;
    static String[] environmentSamples = {
            "Water",
            "Food",
            "Vector"
    };
    static final int NUMBER_OF_CHECKS = environmentSamples.length;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_env_sample, container, false);

        Switch[] options = new Switch[NUMBER_OF_CHECKS];
        options[0] = (Switch) mView.findViewById(R.id.sw_Water);
        options[1] = (Switch) mView.findViewById(R.id.sw_Food);
        options[2] = (Switch) mView.findViewById(R.id.sw_Vector);

        for(int i=0; i<NUMBER_OF_CHECKS; ++i) {
            final int temp = i;
            options[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    FormDataStore.environmentSamplesTaken[temp] = isChecked;
                }
            });
        }

        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
        else {
            if(mView!=null) mView.clearFocus();
        }
    }


}
