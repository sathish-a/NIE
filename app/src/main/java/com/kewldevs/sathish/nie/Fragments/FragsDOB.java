package com.kewldevs.sathish.nie.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.Others.TextValidator;
import com.kewldevs.sathish.nie.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

import static com.kewldevs.sathish.nie.Fragments.FormDataStore.isValidated;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsDOB extends Fragment implements DatePickerDialog.OnDateSetListener {

    View mView;
    double ageYears = 0;

    EditText yrs, months;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.frags_dob, container, false);
        yrs = (EditText) mView.findViewById(R.id.et_age_years);
        months = (EditText) mView.findViewById(R.id.et_age_months);

        ((Button) mView.findViewById(R.id.btnPickDate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        FragsDOB.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        yrs.addTextChangedListener(new TextValidator(yrs) {
            @Override
            public void validate(EditText view, String text) {
                validateAge();
            }
        });

        months.addTextChangedListener(new TextValidator(months) {
            @Override
            public void validate(EditText view, String text) {
                validateAge();
            }
        });

        return mView;
    }

    void validateAge() {
        final Drawable myIcon = ContextCompat.getDrawable(getActivity(), R.drawable.ic_done_black_24dp);
        myIcon.setBounds(0, 0, 50, 50);

        String ageInYears = yrs.getText().toString();
        String ageInMonths = months.getText().toString();
        if (!ageInYears.isEmpty()) {
            try {
                ageYears = Integer.parseInt(ageInYears);
                if (ageYears > 0 && ageYears < 100) {
                    yrs.setError("Good", myIcon);
                    FormDataStore.age = ageYears;
                    isValidated[MainActivity.currentFrag] = true;
                    MainActivity.thumbsUp();
                } else throw new Exception("Enter a reasonable age mofo.");
            } catch (Exception e) {
                yrs.setError("Enter proper age");
                isValidated[MainActivity.currentFrag] = false;
                MainActivity.thumbsDown();
            }
        } else if (!ageInMonths.isEmpty()) {
            ageYears = Double.parseDouble(ageInMonths) / 12.0;
            if (ageYears > 1) {
                months.setError("Enter months between 1 & 12");
                isValidated[MainActivity.currentFrag] = false;
                MainActivity.thumbsDown();
            } else {
                months.setError("Good", myIcon);
                FormDataStore.age = ageYears;
                isValidated[MainActivity.currentFrag] = true;
                MainActivity.thumbsUp();
            }
        }
    }

    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        DateTimeFormatter dtf = DateTimeFormat.forPattern("d/M/yyyy");
        String dateSelected = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        ageYears = calculateAge(LocalDate.now(), dtf.parseLocalDate(dateSelected));
        if(ageYears<=0) {
            isValidated[MainActivity.currentFrag] = false;
            MainActivity.thumbsDown();
            FormDataStore.age = ageYears;
            ((TextView) getView().findViewById(R.id.tv_age_selected)).setText("Select correct DoB");
        } else {
            isValidated[MainActivity.currentFrag] = true;
            MainActivity.thumbsUp();
            ((TextView) getView().findViewById(R.id.tv_age_selected)).setText("Selected Age from DoB : " + ageYears + " years");
        }
    }

    double calculateAge(LocalDate now, LocalDate dob)
    {
        return (now.getYear() + (now.getMonthOfYear() / 12.0)) - (dob.getYear() + (dob.getMonthOfYear() / 12.0));
    }


}
