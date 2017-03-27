package com.kewldevs.sathish.nie.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsSample extends Fragment implements DatePickerDialog.OnDateSetListener {

    View mView;
    int[] switchIDs;
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
    String whatToStore = ""; //This is a very dirty hack; I did this because I'm lazy

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_sample, container, false);
        switchIDs = new int[] {
                R.id.sw_Blood,
                R.id.sw_Serum,
                R.id.sw_Npop,
                R.id.sw_Stool,
                R.id.sw_Sputum,
                R.id.sw_Urine,
                R.id.sw_NotDone
        };

        final Switch[] options = new Switch[NUMBER_OF_CHECKS];
        for(int i=0; i<NUMBER_OF_CHECKS; ++i) options[i] = (Switch) mView.findViewById(switchIDs[i]);

        for(int i=0; i<NUMBER_OF_CHECKS; ++i) {
            LinearLayout parent = (LinearLayout) options[i].getParent();
            Button addTestDate = new Button(getContext());
            addTestDate.setText("Select test date");
            final int temp = i;
            addTestDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    whatToStore = "T"+ temp; //'T' means Test Date
                    showCal();
                }
            });

            Button addResultDate = new Button(getContext());
            addResultDate.setText("Select Result Date");
            addResultDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    whatToStore = "R"+ temp; //'R' means Result Date.. Sobba
                    showCal();
                }
            });

            addResultDate.setVisibility(View.GONE);
            addTestDate.setVisibility(View.GONE);
            parent.addView(addTestDate);
            parent.addView(addResultDate);
        }

        for(int i=0; i<NUMBER_OF_CHECKS; ++i) {
            final int temp = i;
            options[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    FormDataStore.samplesTaken[temp] = isChecked;
                    //Validation:
                    count = isChecked ? ++count : --count;
                    FormDataStore.isValidated[MainActivity.currentFrag] = count>0;
                    if(count>0) MainActivity.thumbsUp();
                    else MainActivity.thumbsDown();

                    LinearLayout parent = (LinearLayout) options[temp].getParent();
                    for(int i=1; i<=4; ++i)
                        if(parent.getChildAt(i) != null)
                            parent.getChildAt(i).setVisibility(isChecked ? View.VISIBLE : View.GONE);

                }
            });
        }
        return mView;
    }

    private void showCal() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FragsSample.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMaxDate(now);
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if(whatToStore.isEmpty()) return;
        char c = whatToStore.charAt(0);
        int i = whatToStore.charAt(1) - '0';
        LinearLayout container = (LinearLayout) ((Switch) mView.findViewById(switchIDs[i])).getParent();
        String dateSelected = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        int childLocation = -1;

        if(c=='T') {
            FormDataStore.testTakenDate[i] = dateSelected;
            childLocation = 3;
            dateSelected = "Test taken on: "+dateSelected; //Hereafter, don't use dateSelected for date
        } else if(c=='R') {
            FormDataStore.testResultDate[i] = dateSelected;
            childLocation = 4;
            dateSelected = "Test result on: "+dateSelected;
            if(container.getChildAt(childLocation-1) == null) container.addView(getNewEditText(), childLocation-1);
        } else return;
        EditText et = (EditText) container.getChildAt(childLocation);
        if(et == null) {
            et = getNewEditText();
            container.addView(et, childLocation);
        }
        et.setText(dateSelected);
    }

    EditText getNewEditText() {
        EditText et = new EditText(getContext());
        //et.setEnabled(false);
        et.setClickable(false);
        et.setFocusable(false);
        et.setTextColor(Color.BLACK);
        return  et;
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
