package com.kewldevs.sathish.nie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.kewldevs.sathish.nie.Others.Connections;
import com.kewldevs.sathish.nie.Others.Helper;
import com.kewldevs.sathish.nie.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ((EditText) findViewById(R.id.et_host_ip)).setText(Connections.hostIP.replace("http://", ""));
        ((Switch) findViewById(R.id.switch_skip_login)).setChecked(LoginActivity.skipLogin);
    }

    public void saveSettings(View view) {
        Connections.hostIP = "http://"+((EditText) findViewById(R.id.et_host_ip)).getText().toString();
        getPreferences(MODE_PRIVATE).edit().putString(Helper.HOST_IP_KEY, Connections.hostIP).apply();
        LoginActivity.skipLogin = ((Switch) findViewById(R.id.switch_skip_login)).isChecked();
        getPreferences(MODE_PRIVATE).edit().putBoolean(Helper.SKIP_LOGIN_KEY, LoginActivity.skipLogin).apply();
        Toast.makeText(getApplicationContext(), "Settings saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
