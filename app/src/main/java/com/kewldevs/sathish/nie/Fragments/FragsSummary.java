package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kewldevs.sathish.nie.R;

/**
 * Created by sathish on 3/23/17.
 */

public class FragsSummary extends Fragment {

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_summary, container, false);

        //TODO: Show all the form fields and corresponding values

        return mView;
    }


    public void showSummary()
    {
        /*
        *  public static String deptName = ""; //can be int
    public static String regNo = ""; //can be long
    public static String patientName = "";
    public static double age = 0;
    public static String gender = ""; //can be int
    public static int countryCode = 91;
    public static long mobNo = 0;

    public static String district = "", taluk = "", village = "";
        *
        * */
        ((EditText) mView.findViewById(R.id.eTPatientname)).setText(FormDataStore.patientName);
        ((EditText) mView.findViewById(R.id.eTAge)).setText(String.valueOf(FormDataStore.age));
        ((EditText) mView.findViewById(R.id.eTDepartment)).setText(FormDataStore.deptName);
        ((EditText) mView.findViewById(R.id.eTDateofadmission)).setText(FormDataStore.admissionDate);
        ((EditText) mView.findViewById(R.id.eTGender)).setText(FormDataStore.gender);
        ((EditText) mView.findViewById(R.id.eTMobilenumber)).setText(String.valueOf(FormDataStore.countryCode)
                +" "+String.valueOf(FormDataStore.mobNo));
        ((EditText) mView.findViewById(R.id.eTRegNO)).setText(FormDataStore.regNo);
        ((EditText) mView.findViewById(R.id.eTDistrict)).setText(FormDataStore.district);
        ((EditText) mView.findViewById(R.id.eTDoornumber)).setText(FormDataStore.address[0]);
        ((EditText) mView.findViewById(R.id.eTStreetname)).setText(FormDataStore.address[1]);
        ((EditText) mView.findViewById(R.id.eTArea)).setText(FormDataStore.address[2]);
        ((EditText) mView.findViewById(R.id.eTLocality)).setText(FormDataStore.address[3]);
        ((EditText) mView.findViewById(R.id.eTCity)).setText(FormDataStore.address[4]);
        ((EditText) mView.findViewById(R.id.eTTaluk)).setText(FormDataStore.taluk);

        //for(int i=0; i)
        ((EditText) mView.findViewById(R.id.eTSymptoms)).setText("");
        ((EditText) mView.findViewById(R.id.eTEnvSymptoms)).setText("");
        ((EditText) mView.findViewById(R.id.eTAdditionalsymptoms)).setText("");





    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            int i;
            for(i=0; i<FormDataStore.NUMBER_OF_SECTIONS; ++i) if(!FormDataStore.isValidated[i]) break;
            if(i==FormDataStore.NUMBER_OF_SECTIONS)
            {
                showSummary();
            } else {

            }
        }
        else {
            if(mView!=null) mView.clearFocus();
        }
    }

}
