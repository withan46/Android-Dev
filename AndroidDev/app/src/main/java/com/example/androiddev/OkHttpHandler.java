package com.example.androiddev;

import android.os.*;
import android.widget.Toast;

import org.json.*;
import java.util.*;
import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    String findClinic(String url) throws Exception {
        String clinic_vat_number = "";
        String data = "";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()) {
            data = response.body().string();
            try {
                JSONObject json = new JSONObject(data);
                Iterator<String> keys = json.keys();
                while(keys.hasNext()) {
                    String key = keys.next();
                    clinic_vat_number = json.get(key).toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return clinic_vat_number;
    }

    public boolean createNewService(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        return response.isSuccessful();
    }

    public boolean add_patient(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        System.out.println("My Response: " + response);

        return response.isSuccessful();
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
