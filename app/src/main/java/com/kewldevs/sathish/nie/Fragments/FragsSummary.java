package com.kewldevs.sathish.nie.Fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;

import static com.kewldevs.sathish.nie.Fragments.FormDataStore.*;
import static com.kewldevs.sathish.nie.Others.Helper.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kewldevs.sathish.nie.Activities.MainActivity;
import com.kewldevs.sathish.nie.R;

import java.util.LinkedHashMap;

public class FragsSummary extends Fragment {

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frags_summary, container, false);
        return mView;
    }

    public void showSummary() {

        ((EditText) mView.findViewById(R.id.eTPatientname)).setText(patientName);
        ((EditText) mView.findViewById(R.id.eTAge)).setText(String.valueOf(age));
        ((EditText) mView.findViewById(R.id.eTDepartment)).setText(deptName);
        ((EditText) mView.findViewById(R.id.eTDateofadmission)).setText(admissionDate+" "+admissionTime);
        ((EditText) mView.findViewById(R.id.eTGender)).setText(gender);
        ((EditText) mView.findViewById(R.id.eTMobilenumber)).setText(countryCode +" "+mobNo);
        ((EditText) mView.findViewById(R.id.eTRegNO)).setText(regNo);
        ((EditText) mView.findViewById(R.id.eTDistrict)).setText(district);
        ((EditText) mView.findViewById(R.id.eTDoornumber)).setText(address[0]);
        ((EditText) mView.findViewById(R.id.eTStreetname)).setText(address[1]);
        ((EditText) mView.findViewById(R.id.eTArea)).setText(address[2]);
        ((EditText) mView.findViewById(R.id.eTLocality)).setText(address[3]);
        ((EditText) mView.findViewById(R.id.eTCity)).setText(address[4]);
        ((EditText) mView.findViewById(R.id.eTTaluk)).setText(taluk);

        String symptoms = getCSV(symptomsObserved, FragsSymptoms.symptomNames, FragsSymptoms.NUMBER_OF_CHECKS);
        ((EditText) mView.findViewById(R.id.eTSymptoms)).setText(symptoms);

        String samples = getCSV(samplesTaken, FragsSample.samples, FragsSample.NUMBER_OF_CHECKS);
        ((EditText) mView.findViewById(R.id.eTSamples)).setText(samples);

        String envSamples = getCSV(environmentSamplesTaken, FragsEnvSample.environmentSamples, FragsEnvSample.NUMBER_OF_CHECKS);
        ((EditText) mView.findViewById(R.id.eTEnvSymptoms)).setText(envSamples);

        //TODO:
        ((EditText) mView.findViewById(R.id.eTAdditionalsymptoms)).setText("");

    }

    public void showSummaryDynamic() {
        generateParametersMap();
        LinearLayout container = (LinearLayout) mView.findViewById(R.id.summaryContainer);
        container.removeAllViews();
        container.removeAllViewsInLayout();

        String value = "";
        for(String key: map.keySet()) {
            value = map.get(key);
            if(value != null && !value.isEmpty() && !value.equalsIgnoreCase("false"))
                container.addView(getTextInputLayout(key, value));
        }

        MainActivity.showFormSubmitButton();
    }

    public void generateParametersMap() {

        int i;
        map = new LinkedHashMap<>();
        map.put("PatientName", patientName);
        map.put("RegistrationNumber", regNo);
        map.put("CountryCode", countryCode+"");
        map.put("MobileNumber", mobNo+"");
        map.put("AdmissionDate", admissionDate);
        map.put("AdmissionTime", admissionTime);
        map.put("Department", deptName);
        map.put("Age", age+"");
        map.put("Gender", gender);
        map.put("DistrictName", district);
        map.put("DistrictCode", district_no+"");
        map.put("TalukName", taluk);
        map.put("TalukCode", taluk_no+"");
        map.put("VillageName", village);
        map.put("VillageCode", village_no+"");
        i=0;
        map.put("DoorNumber", address[i++]);
        map.put("Area", address[i++]);
        map.put("Street", address[i++]);
        map.put("Locality", address[i++]);
        map.put("City", address[i++]);
        i=0;
        map.put("SymptomFebrile", String.valueOf(symptomsObserved[i++]));
        map.put("SymptomRespiratory", String.valueOf(symptomsObserved[i++]));
        map.put("SymptomDiarrhoea", String.valueOf(symptomsObserved[i++]));
        i=0;
        map.put("SampleBlood", String.valueOf(samplesTaken[i]));
        map.put("SampleBloodTestDate", testTakenDate[i]);
        map.put("SampleBloodResultDate", testResultDate[i]);
        ++i;
        map.put("SampleSerum", String.valueOf(samplesTaken[i]));
        map.put("SampleSerumTestDate", testTakenDate[i]);
        map.put("SampleSerumResultDate", testResultDate[i]);
        ++i;
        map.put("SampleNPOP", String.valueOf(samplesTaken[i]));
        map.put("SampleNPOPTestDate", testTakenDate[i]);
        map.put("SampleNPOPResultDate", testResultDate[i]);
        ++i;
        map.put("SampleStool", String.valueOf(samplesTaken[i]) );
        map.put("SampleStoolTestDate", testTakenDate[i]);
        map.put("SampleStoolResultDate", testResultDate[i]);
        ++i;
        map.put("SampleSputum", String.valueOf(samplesTaken[i]));
        map.put("SampleSputumTestDate", testTakenDate[i]);
        map.put("SampleSputumResultDate", testResultDate[i]);
        ++i;
        map.put("SampleUrine", String.valueOf(samplesTaken[i]));
        map.put("SampleUrineTestDate", testTakenDate[i]);
        map.put("SampleUrineResultDate", testResultDate[i]);
        ++i;
        map.put("SampleNotDone", String.valueOf(samplesTaken[i]));
        i=0;
        map.put("EnvironmentSampleWater",   String.valueOf(environmentSamplesTaken[i++]));
        map.put("EnvironmentSampleFood",    String.valueOf(environmentSamplesTaken[i++]));
        map.put("EnvironmentSampleVector",  String.valueOf(environmentSamplesTaken[i++]));

    }

    public TextInputLayout getTextInputLayout(String key, String value) {
        EditText et = new EditText(getContext());
        et.setFocusable(false);
        et.setClickable(false);
        et.setHint(key);
        et.setText(value);
        TextInputLayout til = new TextInputLayout(getContext());
        til.addView(et);
        return til;
    }

    String getCSV(boolean[] enabled, String[] names, int N) {
        String csv = "";
        for(int i=0; i<N; ++i){
            if(enabled[i]){
                csv += names[i]+", ";
            }
        }
        return csv;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            int i;
            for(i=0; i<NUMBER_OF_SECTIONS; ++i) if(!isValidated[i]) break;
            if(i==NUMBER_OF_SECTIONS) {
                mView.findViewById(R.id.summaryContainer).setVisibility(View.VISIBLE);
                mView.findViewById(R.id.summaryError).setVisibility(View.GONE);
                //showSummary();
                showSummaryDynamic();
            } else {
                mView.findViewById(R.id.summaryContainer).setVisibility(View.GONE);
                mView.findViewById(R.id.summaryError).setVisibility(View.VISIBLE);
                MainActivity.disableFormSubmitButton();
            }
        } else {
            if(mView!=null) mView.clearFocus();
        }
    }

}
