package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.kewldevs.sathish.nie.Fragments.FormDataStore.isValidated;


public class FragsAdmissionDate extends Fragment implements DatePickerDialog.OnDateSetListener {

    //TODO: Store this
    String dateSelected = "";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.frags_admission, container, false);
//        if(FormDataStore.admissionDate.isEmpty())
//        showCal();
        /*DateFormat df = new SimpleDateFormat("d/M/yyyy");
        Date dateobj = new Date();
        Snackbar.make(view.findViewById(R.id.snackbarPosition), df.format(dateobj), Snackbar.LENGTH_INDEFINITE).show();*/
        ((Button) view.findViewById(R.id.btnPickDate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCal();
            }
        });

        return view;
    }

    private void showCal() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FragsAdmissionDate.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        TextView disp = (TextView ) getView().findViewById(R.id.tv_date_selected);
        dateSelected = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        disp.setText("Selected Date: "+dateSelected);
        //Snackbar.make(getView().findViewById(R.id.snackbarPosition), dateSelected, Snackbar.LENGTH_INDEFINITE).show();

        //Validation occurs here itself
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");

        try {
            if(sdf.parse(dateSelected).getTime() > new Date().getTime()) {
                disp.setText("ERROR: SET A VALID DATE!!");
                isValidated[MainActivity.currentFrag] = false;
                MainActivity.thumbsDown();
            } else {
                FormDataStore.admissionDate = dateSelected;
                isValidated[MainActivity.currentFrag] = true;
                MainActivity.thumbsUp();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            disp.setText("Internal App Error");
        }

    }


}
