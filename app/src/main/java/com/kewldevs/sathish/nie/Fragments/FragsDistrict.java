package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.Others.Helper;
import com.kewldevs.sathish.nie.Others.MyDatabase;
import com.kewldevs.sathish.nie.R;

import java.util.ArrayList;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsDistrict extends Fragment {

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_district, container, false);
        setupAutoComplete();
        return mView;
    }

    void setupAutoComplete() {

        new Thread() {
            public void run() {

                final AutoCompleteTextView actv = (AutoCompleteTextView) mView.findViewById(R.id.actv_district);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, readDistricts());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        actv.setAdapter(adapter);
                        actv.setThreshold(0);
                        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String temp[] = parent.getItemAtPosition(position).toString().split(":");
                                try {
                                    FormDataStore.district = temp[0];
                                    FormDataStore.district_no = Integer.parseInt(temp[1]);
                                    actv.setText(FormDataStore.district);
                                } catch (Exception e) {
                                    Log.d(Helper.TAG, "Error Parsing..");
                                    Toast.makeText(getContext(), "Error Parsing..", Toast.LENGTH_LONG).show();
                                }
                                Log.d("Autocomplete: ", "Position:"+FormDataStore.district_no+" Name:"+FormDataStore.district);
                                FormDataStore.taluk_no = -1; //reset taluk
                                FormDataStore.isValidated[MainActivity.currentFrag] = true;
                                MainActivity.thumbsUp();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    ArrayList<String> readDistricts() {
        Log.d(Helper.TAG, "Populating Districts...");
        MyDatabase db = MyDatabase.getInstance(getActivity().getApplicationContext());
        return db.getDistricts();
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
