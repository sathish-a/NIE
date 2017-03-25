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

public class FragsSymptoms extends Fragment {

    View mView;
    static final int NUMBER_OF_CHECKS = 3;
    static String[] symptomNames = new String[] {
                "Acute febrile illness",
                "Acute Respiratory illness",
                "Acute diarrhoea illness"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_symptoms, container, false);

        Switch[] options = new Switch[NUMBER_OF_CHECKS];
        options[0] = (Switch) mView.findViewById(R.id.sw_Febrile);
        options[1] = (Switch) mView.findViewById(R.id.sw_Resp);
        options[2] = (Switch) mView.findViewById(R.id.sw_Diarrhoea);
        FormDataStore.isValidated[MainActivity.currentFrag] = true; //No need of validation
        MainActivity.thumbsUp();
        for(int i=0; i<NUMBER_OF_CHECKS; ++i) {
            final int temp = i;
            options[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    FormDataStore.symptomsObserved[temp] = isChecked;
                }
            });
        }

        return mView;
    }

}
