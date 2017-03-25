package com.kewldevs.sathish.nie.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.R;

import static com.kewldevs.sathish.nie.Others.Helper.CLEAR_COLOR;
import static com.kewldevs.sathish.nie.Others.Helper.SELECTED_COLOR;
import static com.kewldevs.sathish.nie.Others.Helper.TAG;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsGender extends Fragment implements View.OnClickListener{

    View mView;
    TextView mMale,mFemale,mTrans;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.frags_gender, container, false);
        mMale = (TextView) mView.findViewById(R.id.tv_Male);
        mFemale = (TextView) mView.findViewById(R.id.tv_Female);
        mTrans = (TextView) mView.findViewById(R.id.tv_Trans);
        mMale.setOnClickListener(this);
        mFemale.setOnClickListener(this);
        mTrans.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_Male:
                Select(mMale);
                break;
            case R.id.tv_Female:
                Select(mFemale);
                break;
            case R.id.tv_Trans:
                Select(mTrans);
                break;

        }

    }

    private void Select(TextView v)
    {
        FormDataStore.isValidated[MainActivity.currentFrag] = true;
        FormDataStore.gender = (String) v.getText();
        Log.d(TAG, "Selected: "+FormDataStore.gender);
        MainActivity.thumbsUp();
        clearSelection();
        v.setBackgroundColor(Color.parseColor(SELECTED_COLOR));
    }

    private void clearSelection() {
        mMale.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
        mFemale.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
        mTrans.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
    }

    //TODO: Only if something is selected, isValidated[MainActivity.currentFrag] = true;
    // FormDataStore.gender = whatever

}
