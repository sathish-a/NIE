package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.Others.Helper;
import com.kewldevs.sathish.nie.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.kewldevs.sathish.nie.Fragments.FormDataStore.isValidated;

public class FragsAdmissionDate extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    static final int NUMBER_OF_CHECKS = 2;
    boolean[] validaion = new boolean[NUMBER_OF_CHECKS];

    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_admission, container, false);

        /*DateFormat df = new SimpleDateFormat("d/M/yyyy");
        Date dateobj = new Date();
        Snackbar.make(view.findViewById(R.id.snackbarPosition), df.format(dateobj), Snackbar.LENGTH_INDEFINITE).show();*/
        ((Button) mView.findViewById(R.id.btnPickDate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });

        ((Button) mView.findViewById(R.id.btnPickTime)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        return mView;
    }

    void showTimePicker() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                FragsAdmissionDate.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                false //not 24 hours
        );
        tpd.show(getActivity().getFragmentManager(), "Timepickerdialog");
    }

    private void showCalendar() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FragsAdmissionDate.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMaxDate(now);
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        TextView disp = (TextView ) getView().findViewById(R.id.tv_date_selected);
        String dateSelected = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        disp.setText(dateSelected);
        //Snackbar.make(getView().findViewById(R.id.snackbarPosition), dateSelected, Snackbar.LENGTH_INDEFINITE).show();

        //Validation occurs here itself
        /*SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");

        try {
            if(sdf.parse(dateSelected).getTime() > new Date().getTime()) {
                disp.setText("ERROR: SET A VALID DATE!!");
                validaion[0] = false;
                checkIfCompletelyValidated();
            } else {*/
                FormDataStore.admissionDate = dateSelected;
                validaion[0] = true;
                Log.d(Helper.TAG, "onDateSet: "+dateSelected);
                checkIfCompletelyValidated();
            /*}
        } catch (ParseException e) {
            e.printStackTrace();
            disp.setText("Internal App Error");
        }*/

    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        FormDataStore.admissionTime = hourOfDay+":"+minute;
        ((TextView ) getView().findViewById(R.id.tv_time_selected)).setText(FormDataStore.admissionTime);
        validaion[1] = true;
        checkIfCompletelyValidated();
    }

    void checkIfCompletelyValidated() {
        int i;
        for(i=0; i<NUMBER_OF_CHECKS; ++i) if(!validaion[i]) break;

        if(i == NUMBER_OF_CHECKS) {
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
