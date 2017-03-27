package com.kewldevs.sathish.nie.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
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

public class FragsMobNo extends Fragment {

    View mView;
    int NUMBER_OF_CHECKS = 2;
    boolean[] validaion = new boolean[NUMBER_OF_CHECKS];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_mob_no, container, false);
        final Drawable myIcon = ContextCompat.getDrawable(getActivity(), R.drawable.ic_done_black_24dp);
        myIcon.setBounds(0, 0, 50, 50);

        EditText cc = (EditText) mView.findViewById(R.id.et_CountryCode);
        EditText phone = (EditText) mView.findViewById(R.id.et_MobNo) ;

        cc.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) });
        cc.setText("91"); validaion[0] = true;
        phone.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });

        cc.addTextChangedListener(new TextValidator(cc) {
            @Override
            public void validate(EditText view, String text) {
                if(text.isEmpty()) {
                    validaion[0] = false;
                    view.setError("Enter Country Code");
                } else {
                    validaion[0] = true;
                    view.setError("OK", myIcon);
                    FormDataStore.countryCode = Integer.parseInt(text.trim());
                }
                checkIfCompletelyValidated();
            }
        });

        phone.addTextChangedListener(new TextValidator(phone) {
            @Override
            public void validate(EditText view, String text) {
                if(text.length() != 10) {
                    validaion[1] = false;
                    view.setError("Enter correct mobile no.");
                } else {
                    validaion[1] = true;
                    view.setError("OK", myIcon);
                    mView.clearFocus();
                    FormDataStore.mobNo = Long.parseLong(text.trim());
                }
                checkIfCompletelyValidated();
            }
        });


        return mView;
    }

    void checkIfCompletelyValidated() {
        int i;
        for(i=0; i<NUMBER_OF_CHECKS; ++i) if(!validaion[i]) break;

        if(i==NUMBER_OF_CHECKS) {isValidated[MainActivity.currentFrag] = true; MainActivity.thumbsUp();}
        else {isValidated[MainActivity.currentFrag] = false; MainActivity.thumbsDown();}

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
