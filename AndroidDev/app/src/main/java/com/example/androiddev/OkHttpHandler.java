package com.example.androiddev;

import android.os.*;
import android.widget.Toast;

import org.json.*;

import java.io.IOException;
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

    ArrayList<Patient> populateScrollView(String url) throws Exception {
        ArrayList<Patient> patientsList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));

        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

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
                    caseType = "-";
                }
                String nextAppointment;
                if (!jsonObject.isNull("next_appointment_date")) {
                    nextAppointment = jsonObject.getString("next_appointment_date");
                } else {
                    nextAppointment = "-";
                }
                String nextAppointmentTime;
                if (!jsonObject.isNull("next_appointment_time")) {
                    nextAppointmentTime = jsonObject.getString("next_appointment_time");
                } else {
                    nextAppointmentTime = "";
                }
                String historyDate;
                if(!jsonObject.isNull("history_date")) {
                    historyDate = jsonObject.getString(("history_date"));
                } else {
                    historyDate = "";
                }
                List<String> history = new ArrayList<>();
                String historyTime;
                if(!jsonObject.isNull("history_time")) {
                    historyTime = jsonObject.getString(("history_time"));
                    history.add(historyDate + " " + historyTime);
                } else {
                    historyTime = "";
                }

                patientsList.add(new Patient(name, email, ssn, phone, nextAppointment, nextAppointmentTime, caseType, history));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return patientsList;
    }

    public ArrayList<weeklyPlannerDataR6> populateWeeklyPlanner (String url) throws IOException {
        ArrayList<weeklyPlannerDataR6> weeklyPlannerDatumR6s = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            List<String> times = new ArrayList<>();
            List<String> tos = new ArrayList<>();
            List<String> names = new ArrayList<>();

            while(keys.hasNext()) {
                String date = keys.next();
                times = Arrays.asList(json.getJSONObject(date).getString("grouped_times").split(","));
                tos = Arrays.asList(json.getJSONObject(date).getString("grouped_tos").split(","));
                names = Arrays.asList(json.getJSONObject(date).getString("grouped_names").split(","));
                for (int i=0 ; i< times.size() ; i++)
                {
                    weeklyPlannerDatumR6s.add(new weeklyPlannerDataR6(times.get(i), tos.get(i), names.get(i)));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weeklyPlannerDatumR6s;
    }
}
