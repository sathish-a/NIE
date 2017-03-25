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

public class FragsSample extends Fragment {

    View mView;
    static String[] samples = {
            "Blood",
            "Serum",
            "Np/Op",
            "Stool",
            "Sputum",
            "Urine",
            "Not done"
    };
    static final int NUMBER_OF_CHECKS = samples.length;
    int count=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_sample, container, false);

        Switch[] options = new Switch[NUMBER_OF_CHECKS];
        options[0] = (Switch) mView.findViewById(R.id.sw_Blood);
        options[1] = (Switch) mView.findViewById(R.id.sw_Serum);
        options[2] = (Switch) mView.findViewById(R.id.sw_Npop);
        options[3] = (Switch) mView.findViewById(R.id.sw_Stool);
        options[4] = (Switch) mView.findViewById(R.id.sw_Sputum);
        options[5] = (Switch) mView.findViewById(R.id.sw_Urine);
        options[6] = (Switch) mView.findViewById(R.id.sw_NotDone);

        for(int i=0; i<NUMBER_OF_CHECKS; ++i) {
            final int temp = i;
            options[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    FormDataStore.samplesTaken[temp] = isChecked;
                    //Validation:
                    count = isChecked ? ++count : --count;
                    FormDataStore.isValidated[MainActivity.currentFrag] = count>0;
                }
            });
        }
        MainActivity.thumbsUp();


        return mView;
    }

}
