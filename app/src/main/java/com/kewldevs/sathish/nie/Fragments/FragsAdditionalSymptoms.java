package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kewldevs.sathish.nie.R;

/**
 * Created by Jegan on 3/23/17.
 */

public class FragsAdditionalSymptoms extends Fragment {

    View mView;
    final int NUMBER_OF_SYMPTOMS=1;
    EditText[] symptomDays, symptomHours;
    Button[] symptomDaysMinus, symptomHoursMinus, symptomDaysPlus, symptomHoursPlus;
    CheckBox[] yesCheckBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_additional_symptoms, container, false);
        symptomDays = new EditText[NUMBER_OF_SYMPTOMS];
        symptomHours = new EditText[NUMBER_OF_SYMPTOMS];
        symptomDaysMinus = new Button[NUMBER_OF_SYMPTOMS];
        symptomHoursMinus = new Button[NUMBER_OF_SYMPTOMS];
        symptomDaysPlus = new Button[NUMBER_OF_SYMPTOMS];
        symptomHoursPlus = new Button[NUMBER_OF_SYMPTOMS];
        yesCheckBox = new CheckBox[NUMBER_OF_SYMPTOMS];
        for(int i=0; i<NUMBER_OF_SYMPTOMS; i++)
        {
            symptomDays[i] = (EditText)mView.findViewById(getResources().getIdentifier("Symptom"+(i+1)+"DaysEditText", "id", getActivity().getPackageName()));
            symptomHours[i] = (EditText)mView.findViewById(getResources().getIdentifier("Symptom"+(i+1)+"HoursEditText", "id", getActivity().getPackageName()));
            symptomDaysMinus[i] = (Button)mView.findViewById(getResources().getIdentifier("Symptom"+(i+1)+"DaysMinusButton", "id", getActivity().getPackageName()));
            symptomDaysPlus[i] = (Button)mView.findViewById(getResources().getIdentifier("Symptom"+(i+1)+"DaysPlusButton", "id", getActivity().getPackageName()));
            symptomHoursMinus[i] = (Button)mView.findViewById(getResources().getIdentifier("Symptom"+(i+1)+"HoursMinusButton", "id",getActivity(). getPackageName()));
            symptomHoursPlus[i] = (Button)mView.findViewById(getResources().getIdentifier("Symptom"+(i+1)+"HoursPlusButton", "id", getActivity().getPackageName()));
            yesCheckBox[i] = (CheckBox)mView.findViewById(getResources().getIdentifier("Symptom"+(i+1)+"YesCheckBox", "id", getActivity().getPackageName()));
        }
        for(int i=0; i<NUMBER_OF_SYMPTOMS; i++)
        {
            symptomDaysMinus[i].setEnabled(false);
            symptomDays[i].setEnabled(false);
            symptomDaysPlus[i].setEnabled(false);
            symptomHoursMinus[i].setEnabled(false);
            symptomHours[i].setEnabled(false);
            symptomHoursPlus[i].setEnabled(false);
            symptomDaysMinus[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String minusButtonClicked = getResources().getResourceName(v.getId());
                    int i = Integer.parseInt(minusButtonClicked.replaceAll("\\D+",""));
                    i--;
                    int days = Integer.parseInt(symptomDays[i].getText().toString());
                    if(days>0)
                        symptomDays[i].setText(String.valueOf(days-1));
                }
            });
            symptomHoursMinus[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String minusButtonClicked = getResources().getResourceName(v.getId());
                    int i = Integer.parseInt(minusButtonClicked.replaceAll("\\D+",""));
                    i--;
                    int hours = Integer.parseInt(symptomHours[i].getText().toString());
                    if(hours>0)
                        symptomHours[i].setText(String.valueOf(hours-1));
                }
            });
            symptomDaysPlus[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String plusButtonClicked = getResources().getResourceName(v.getId());
                    int i = Integer.parseInt(plusButtonClicked.replaceAll("\\D+",""));
                    i--;
                    int days = Integer.parseInt(symptomDays[i].getText().toString());
                    //if()
                    symptomDays[i].setText(String.valueOf(days+1));
                }
            });
            symptomHoursPlus[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String plusButtonClicked = getResources().getResourceName(v.getId());
                    int i = Integer.parseInt(plusButtonClicked.replaceAll("\\D+",""));
                    i--;
                    int hours = Integer.parseInt(symptomHours[i].getText().toString());
                    if(hours!=23)
                        symptomHours[i].setText(String.valueOf(hours+1));
                    else
                    {
                        symptomHours[i].setText("0");
                        symptomDays[i].setText(String.valueOf(Integer.parseInt(symptomDays[i].getText().toString())+1));
                    }
                }
            });
            yesCheckBox[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String yesCheckBoxClicked = getResources().getResourceName(v.getId());
                    int i = Integer.parseInt(yesCheckBoxClicked.replaceAll("\\D+",""));
                    i--;
                    if(((CheckBox)v).isChecked())
                    {
                        symptomDaysMinus[i].setEnabled(true);
                        symptomDays[i].setEnabled(true);
                        symptomDaysPlus[i].setEnabled(true);
                        symptomHoursMinus[i].setEnabled(true);
                        symptomHours[i].setEnabled(true);
                        symptomHoursPlus[i].setEnabled(true);
                    }
                    else
                    {
                        symptomDaysMinus[i].setEnabled(false);
                        symptomDays[i].setEnabled(false);
                        symptomDaysPlus[i].setEnabled(false);
                        symptomHoursMinus[i].setEnabled(false);
                        symptomHours[i].setEnabled(false);
                        symptomHoursPlus[i].setEnabled(false);
                    }
                }
            });
        }
        return mView;
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
