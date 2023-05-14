package com.example.androiddev;

import android.os.StrictMode;
import android.os.*;
import org.json.*;
import java.util.*;

import com.example.androiddev.Patient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler {
    public OkHttpHandler(){
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    ArrayList<Patient> populateScrollView(String url) throws Exception {
        ArrayList<Patient> patientsList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));

        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("My Response: " + data);
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String email = jsonObject.getString("email");
                String ssn = jsonObject.getString("ssn");
                String phone = jsonObject.getString("phone");
                String caseType;
                if (!jsonObject.isNull("case")) {
                    caseType = jsonObject.getString("case");
                } else {
                    caseType = null;
                }
                String nextAppointment;
                if (!jsonObject.isNull("next_appointment_date")) {
                    nextAppointment = jsonObject.getString("next_appointment_date");
                } else {
                    nextAppointment = null;
                }
                String nextAppointmentTime;
                if (!jsonObject.isNull("next_appointment_time")) {
                    nextAppointmentTime = jsonObject.getString("next_appointment_time");
                } else {
                    nextAppointmentTime = null;
                }
                patientsList.add(new Patient(name, email, ssn, phone, nextAppointment, nextAppointmentTime, caseType));
                System.out.println(name + email + ssn + phone + caseType + nextAppointment + nextAppointmentTime);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patientsList;
    }

}


