package com.kewldevs.sathish.nie.Others;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by sathish on 3/27/17.
 */

public class Conn {

    public static final String TAG = "OkHttp";
    public static String hostIP = "http://132.147.164.56";
    public static String loginURL = hostIP+"/NIE/login.jsp";
    public static String formsURL = "/NIE/forms.jsp";
    public static String submitFormURL = "/NIE/formhandler.jsp";
    public static String testSource = "http://github.com/GokulNC/Programming_Practice/blob/master/To%20Solve.txt";

    /*//For Volley:
    static RequestQueue requestQueue;

    static void initConnection(Context context) {
        //Important Note: Call this first before GET or POST
        requestQueue = Volley.newRequestQueue(context);
    }*/

    static OkHttpClient client = new OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .build();


    static void initOkHttpClient() {
        client = new OkHttpClient().newBuilder()
                .followRedirects(true)
                .followSslRedirects(true)
                .build();
    }

    static String doGetRequest(String url) throws IOException {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();

        Log.d(TAG, "Sending GET Request to "+url);

        if(client==null) initOkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();

        Log.d(TAG, "Response Code: "+response.code());
        Log.d(TAG, "Response Message: "+response.message());
        Log.d(TAG, "Response Body: "+response.body().string());
        return response.body().toString();
    }


    public static String doPost(String Url, HashMap<String,String> hashMap)
    {
        String x = "";
        POST p = new POST(Url,hashMap);
        try {
            x =  p.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return x.trim();

    }



    static class POST extends AsyncTask<Void,Void,String>
    {
        String url;

        HashMap<String, String> map;

        public POST(String Url,HashMap<String, String> map)
        {
            url = Url;
            this.map = map;
        }

        @Override
        protected String doInBackground(Void... voids) {

            okhttp3.Response response=null;

            try {
                Log.d(TAG, "doInBackground: POSTING TO "+loginURL);
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                for(String key: map.keySet()) {
                    bodyBuilder.add(key, map.get(key));
                }

                RequestBody body = bodyBuilder.build();

                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                if(client==null) initOkHttpClient();
                response = client.newCall(request).execute();
                return response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";

        }
    }


}