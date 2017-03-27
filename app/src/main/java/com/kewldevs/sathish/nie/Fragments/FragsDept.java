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


public class FragsDept extends Fragment implements View.OnClickListener{

    View mView;
    TextView mInPatient,mOutPatient,mField,mCasualty,mLab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_dept, container, false);

        mInPatient = (TextView) mView.findViewById(R.id.tv_InPatient);
        mOutPatient = (TextView) mView.findViewById(R.id.tv_OutPatient);
        mField = (TextView) mView.findViewById(R.id.tv_Field);
        mCasualty = (TextView) mView.findViewById(R.id.tv_Casualty);
        mLab = (TextView) mView.findViewById(R.id.tv_Lab);

        mInPatient.setOnClickListener(this);
        mOutPatient.setOnClickListener(this);
        mField.setOnClickListener(this);
        mCasualty.setOnClickListener(this);
        mLab.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tv_InPatient:
                Select(mInPatient);
                break;
            case R.id.tv_OutPatient:
                Select(mOutPatient);
                break;
            case R.id.tv_Field:
                Select(mField);
                break;
            case R.id.tv_Lab:
                Select(mLab);
                break;
            case R.id.tv_Casualty:
                Select(mCasualty);
                break;


        }

    }

    private void Select(TextView v)
    {
        FormDataStore.isValidated[MainActivity.currentFrag] = true;
        FormDataStore.deptName = (String) v.getText();
        Log.d(TAG, "Selected: "+FormDataStore.deptName);
        MainActivity.thumbsUp();
        clearSelection();
        v.setBackgroundColor(Color.parseColor(SELECTED_COLOR));
    }

    private void clearSelection() {
        mInPatient.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
        mOutPatient.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
        mField.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
        mCasualty.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
        mLab.setBackgroundColor(Color.parseColor(CLEAR_COLOR));
    }

    //TODO: Only if something is selected, isValidated[MainActivity.currentFrag] = true;
    // FormDataStore.deptName = whatever

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
