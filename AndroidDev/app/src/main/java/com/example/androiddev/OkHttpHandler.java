package com.example.androiddev;

import android.os.*;
import org.json.*;

import java.io.IOException;
import java.util.*;
import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<AcceptedAppointments> getAcceptedAppointments(String url) throws Exception {

        // Array List that will contain all the Accepted Appointments
        ArrayList<AcceptedAppointments> aa = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        // Fetching Data from PHP
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            List<String> times = new ArrayList<>();
            List<String> dates = new ArrayList<>();
            List<String> ssn = new ArrayList<>();
            List<String> names = new ArrayList<>();
            List<String> tos = new ArrayList<>();

            while(keys.hasNext()) {
                String clinic_vat_number = keys.next();
                times = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_times").toString().split(","));
                dates = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_dates").toString().split(","));
                ssn = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_ssn").toString().split(","));
                names = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_names").toString().split(","));
                tos = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_tos").toString().split(","));
            }

            for (int i=0; i<ssn.size(); i++)
            {
                aa.add(new AcceptedAppointments(names.get(i),ssn.get(i),dates.get(i), times.get(i),tos.get(i),"" ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aa;
    }

    public void addHistory (String url) throws Exception
    {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
    }
}
