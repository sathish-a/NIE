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

public class FragsTaluks extends Fragment {

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_taluks, container, false);
        return mView;
    }

    void setupAutoComplete() {

        new Thread() {
            public void run() {

                final AutoCompleteTextView actv = (AutoCompleteTextView) mView.findViewById(R.id.actv_taluk);
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, readTaluks());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        actv.setEnabled(true);
                        actv.setText("");
                        actv.setAdapter(adapter);
                        actv.setThreshold(0);
                        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String temp[] = parent.getItemAtPosition(position).toString().split(":");
                                try {
                                    FormDataStore.taluk = temp[0];
                                    FormDataStore.taluk_no = Integer.parseInt(temp[1]);
                                    actv.setText(FormDataStore.taluk);
                                } catch (Exception e) {
                                    Log.d(Helper.TAG, "Error Parsing..");
                                    Toast.makeText(getContext(), "Error Parsing..", Toast.LENGTH_LONG).show();
                                }
                                Log.d("Autocomplete: ", "Position:"+FormDataStore.taluk_no+" Name: "+FormDataStore.taluk);
                                setupVillageAutoComplete();
                            }
                        });
                    }
                });
            }
        }.start();
    }

    ArrayList<String> readTaluks() {
        Log.d(Helper.TAG, "Populating Taluks...");
        MyDatabase db = MyDatabase.getInstance(null);
        return db.getTaluks(FormDataStore.district_no);
    }

    void setupVillageAutoComplete() {

        new Thread() {
            public void run() {

                final AutoCompleteTextView actv = (AutoCompleteTextView) mView.findViewById(R.id.actv_village);
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, readVillages());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        actv.setAdapter(adapter);
                        actv.setThreshold(0);
                        actv.setEnabled(true);
                        actv.setText("");
                        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String temp[] = parent.getItemAtPosition(position).toString().split(":");
                                try {
                                    FormDataStore.village = temp[0];
                                    FormDataStore.village_no = Integer.parseInt(temp[1]);
                                    actv.setText(FormDataStore.village);
                                } catch (Exception e) {
                                    Log.d(Helper.TAG, "Error Parsing..");
                                    Toast.makeText(getContext(), "Error Parsing..", Toast.LENGTH_LONG).show();
                                }
                                Log.d("Autocomplete: ", "Position:"+FormDataStore.village_no+" Name:"+FormDataStore.village);
                                MainActivity.thumbsUp();
                                FormDataStore.isValidated[MainActivity.currentFrag] = true;
                            }
                        });
                    }
                });
            }
        }.start();
    }

    ArrayList<String> readVillages() {
        Log.d(Helper.TAG, "Populating Villages...");
        MyDatabase db = MyDatabase.getInstance(null);
        return db.getVillages(FormDataStore.district_no, FormDataStore.taluk_no);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if(FormDataStore.district_no == -1) return;
            if(mView!=null && FormDataStore.taluk_no == -1) setupAutoComplete();
        } else {
            if(mView!=null) mView.clearFocus();
        }
    }

}
