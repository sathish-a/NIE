package com.kewldevs.sathish.nie.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.kewldevs.sathish.nie.Others.Connections;
import com.kewldevs.sathish.nie.Others.Helper;
import com.kewldevs.sathish.nie.R;

public class SplashActivity extends AppCompatActivity {


    private static final long SPLASH_TIME_OUT = 3000;

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    boolean showSplash = true;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Connections.hostIP = getPreferences(MODE_PRIVATE).getString(Helper.HOST_IP_KEY, Connections.hostIP);
        if(showSplash) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);
            VideoView view = (VideoView)findViewById(R.id.videoView);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.splash_animation;
            view.setVideoURI(Uri.parse(path));
            view.start();
        }

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                if(!sharedPreferences.getBoolean(Helper.Is_LOGGED_IN_KEY, false))
                {
                    Log.d(Helper.TAG, "onCreate: Not Logged In ");
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    SplashActivity.this.finish();
                }
                else
                {
                    Log.d(Helper.TAG, "onCreate: Logged In ");
                    SplashActivity.this.finish();
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
            }
        }, showSplash ? SPLASH_TIME_OUT : 0);
    }


}
