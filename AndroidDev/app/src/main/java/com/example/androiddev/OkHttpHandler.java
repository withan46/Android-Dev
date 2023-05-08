package com.example.androiddev;

import android.os.*;
import org.json.*;
import java.util.*;
import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<Clinic> populateClinicDropDown(String url) throws Exception {

        ArrayList<Clinic> clinics = new ArrayList<>();

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
            while(keys.hasNext()) {
                String vatNumber = keys.next();
                String[] clinicInfo = json.get(vatNumber).toString().split("-");
                clinics.add(new Clinic(Integer.parseInt(vatNumber), clinicInfo[0], clinicInfo[1], clinicInfo[2], clinicInfo[3]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clinics;
    }

    public ArrayList<Service> populateServiceDropDown(String url) throws Exception {

        ArrayList<Service> services = new ArrayList<>();

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
            while(keys.hasNext()) {
                String code = keys.next();
                String[] serviceInfo = json.get(code).toString().split("@");
                System.out.println(serviceInfo[0] + " " + serviceInfo[1] + " " + serviceInfo[2] + " " + serviceInfo[3]);
                services.add(new Service(Integer.parseInt(code), serviceInfo[0], serviceInfo[1], Double.parseDouble(serviceInfo[2]),Integer.parseInt(serviceInfo[3])));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return services;
    }

    public ArrayList<Appointment> getClinicAppointments(String url) throws Exception {

        ArrayList<Appointment> appointments = new ArrayList<>();

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
            while(keys.hasNext()) {
                String appointment = keys.next();
                String[] appointmentInfo = json.get(appointment).toString().split("\\|");
                for(int i=0;i<appointmentInfo.length;i++) {
                    String[] tempData = appointmentInfo[i].split("@");
                    appointments.add(new Appointment(tempData[0], tempData[1], tempData[2], Integer.parseInt(appointment), Boolean.parseBoolean(tempData[3])));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public boolean bookAppointment(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();

        return response.isSuccessful();
    }
}
