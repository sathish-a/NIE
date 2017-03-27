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

public class FragsAddress extends Fragment {

    View mView;
    static final int NUMBER_OF_CHECKS = 5;
    boolean[] validaion = new boolean[NUMBER_OF_CHECKS];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_address, container, false);
        final Drawable myIcon = ContextCompat.getDrawable(getActivity(), R.drawable.ic_done_black_24dp);
        myIcon.setBounds(0, 0, 50, 50);

        EditText[] et = new EditText[NUMBER_OF_CHECKS];
        et[0] = (EditText) mView.findViewById(R.id.et_Door);
        et[1] = (EditText) mView.findViewById(R.id.et_Street);
        et[2] = (EditText) mView.findViewById(R.id.et_Area);
        et[3] = (EditText) mView.findViewById(R.id.et_Locality);
        et[4] = (EditText) mView.findViewById(R.id.et_City);

        for (int i=0; i<NUMBER_OF_CHECKS; ++i) {
            final int finalI = i;
            et[i].addTextChangedListener(new TextValidator(et[finalI]) {
                @Override
                public void validate(EditText view, String text) {
                    if(text.isEmpty()) {
                        validaion[finalI] = false;
                        view.setError("Field Required");
                    } else {
                        validaion[finalI] = true;
                        view.setError("OK", myIcon);
                        FormDataStore.address[finalI] = text;
                    }
                    checkIfCompletelyValidated();
                }
            });
        }


        return mView;
    }

    void checkIfCompletelyValidated() {
        int i;
        for(i=0; i<NUMBER_OF_CHECKS; ++i) if(!validaion[i]) break;

        if(i == NUMBER_OF_CHECKS)
        {
            isValidated[MainActivity.currentFrag] = true ; MainActivity.thumbsUp();
        }else {
            isValidated[MainActivity.currentFrag] = false ; MainActivity.thumbsDown();
        }
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
