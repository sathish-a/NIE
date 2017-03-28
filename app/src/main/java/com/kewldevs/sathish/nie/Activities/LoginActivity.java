package com.kewldevs.sathish.nie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kewldevs.sathish.nie.Others.Connections;
import com.kewldevs.sathish.nie.Others.Helper;
import com.kewldevs.sathish.nie.R;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    private static String TAG = Helper.TAG;
    static boolean skipLogin = false;
    EditText etUsrId , etPass;
    Button btLogin, btGuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        skipLogin = getPreferences(MODE_PRIVATE).getBoolean(Helper.SKIP_LOGIN_KEY, false);
        //Connections.hostIP = getPreferences(MODE_PRIVATE).getString(Helper.HOST_IP_KEY, Connections.hostIP);
        etUsrId = (EditText) findViewById(R.id.etUsrId);
        etPass = (EditText) findViewById(R.id.etPass);
        btLogin = (Button) findViewById(R.id.btLogin);
        btGuest = (Button) findViewById(R.id.btGuest);
        btGuest.setOnClickListener(this);
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.btLogin: login();
                break;
            case R.id.btGuest: guest();
                break;
        }
    }

    private void guest() {
        Log.d(TAG, "guest: ");
    }

    private void login() {
        String usrId = etUsrId.getText().toString() , pass = etPass.getText().toString();
        Log.d(TAG, "login: UserId: "+usrId);
        Log.d(TAG,"login: Pass: "+pass);
        HashMap<String,String> x = new HashMap<String, String>();


        if(skipLogin) {
            Helper.Login(this);
            return;
        } else if(!usrId.contentEquals("") && !pass.contentEquals("")) {
            x.put(Helper.USR_KEY,usrId);
            x.put(Helper.USR_PASS,pass);
            String res = Connections.doPost(Connections.hostIP+Connections.loginURL, x);
            Log.d(TAG, "login: Response:"+res);
            try {
                if (Integer.parseInt(res) == 1) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    Helper.Login(this);
                    return;
                }
            }catch (Exception e) { }
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void openSettings(View view) {
        startActivity(new Intent(LoginActivity.this, SettingsActivity.class));
    }
}
