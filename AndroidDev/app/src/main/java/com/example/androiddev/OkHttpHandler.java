package com.example.androiddev;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.*;
import okhttp3.RequestBody;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public History getPatientHistory(String url) throws Exception {

        History history = null;

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            String patientSSN = "";
            String description = "";
            String time = "";
            String date = "";
            String tos = "";
            while(keys.hasNext()) {
                patientSSN = keys.next();
                description = json.getJSONObject(patientSSN).getString("description").toString();
                time = json.getJSONObject(patientSSN).getString("time").toString();
                date = json.getJSONObject(patientSSN).getString("date").toString();
                tos = json.getJSONObject(patientSSN).getString("tos").toString();
            }
            history = new History(patientSSN, tos, time, date, description);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return history;
    }

}
