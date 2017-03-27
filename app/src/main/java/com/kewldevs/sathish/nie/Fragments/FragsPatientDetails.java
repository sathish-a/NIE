package com.kewldevs.sathish.nie.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.Others.TextValidator;
import com.kewldevs.sathish.nie.R;

import static com.kewldevs.sathish.nie.Fragments.FormDataStore.isValidated;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsPatientDetails extends Fragment {

    View mView;
    int NUMBER_OF_CHECKS = 2;
    boolean[] validation = new boolean[NUMBER_OF_CHECKS];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.frags_patient_details, container, false);

        final Drawable myIcon = ContextCompat.getDrawable(getActivity(), R.drawable.ic_done_black_24dp);
        myIcon.setBounds(0, 0, 50, 50);

        EditText personName = (EditText) mView.findViewById(R.id.et_PatientName);
        final EditText regNo = (EditText) mView.findViewById(R.id.et_RegNo);

        personName.addTextChangedListener(new TextValidator(personName) {
            @Override
            public void validate(EditText view, String text) {
                if(text.matches("[A-Za-z ]+")) {
                    validation[0] = true;
                    FormDataStore.patientName = text;
                    view.setError("OK", myIcon);
                } else {
                    validation[0] = false;
                    view.setError("Use alphabets only");
                }
                checkIfCompletelyValidated();
            }
        });

        regNo.addTextChangedListener(new TextValidator(regNo) {
            @Override
            public void validate(EditText view, String text) {
                if(text.isEmpty()) {
                    validation[1] = false;
                    view.setError("Enter RegNo");
                } else {
                    validation[1] = true;
                    view.setError("OK", myIcon);
                    FormDataStore.regNo = text;

                }
                checkIfCompletelyValidated();
            }
        });

        return mView;
    }

    void checkIfCompletelyValidated() {
        int i;
        for(i=0; i<NUMBER_OF_CHECKS; ++i) if(!validation[i]) break;

        if(i==NUMBER_OF_CHECKS) {isValidated[MainActivity.currentFrag] = true; MainActivity.thumbsUp();}
        else{ isValidated[MainActivity.currentFrag] = false; MainActivity.thumbsDown();}

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
