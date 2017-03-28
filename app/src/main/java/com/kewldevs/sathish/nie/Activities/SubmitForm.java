package com.kewldevs.sathish.nie.Activities;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.kewldevs.sathish.nie.Fragments.FormDataStore;
import com.kewldevs.sathish.nie.Others.Helper;

import static com.kewldevs.sathish.nie.Others.Connections.*;

/**
 * Created by Gokul on 27-Mar-17.
 */

public class SubmitForm implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        //TODO: submitForm(v.getContext());
    }

    public void submitForm(Context c) {
        String response = "";
        if(Helper.isNetworkAvailable(c)) {
            response = doPost(hostIP+staticFormURL, FormDataStore.map);
        } else {
            Toast.makeText(c, "No Internet Access", Toast.LENGTH_SHORT).show();
        }
    }
}
