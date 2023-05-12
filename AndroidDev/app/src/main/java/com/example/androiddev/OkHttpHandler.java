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

            List<String> names;
            List<String> addresses;
            List<String> phoneNumbers;
            List<String> emails;

            while(keys.hasNext()) {
                String vatNumber = keys.next();

                names = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_names").split(","));
                addresses = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_addresses").split(","));
                phoneNumbers = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_numbers").split(","));
                emails = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_emails").split(","));

                for(int i=0;i<names.size();i++) {
                    clinics.add(new Clinic(Integer.parseInt(vatNumber), names.get(i), addresses.get(i), phoneNumbers.get(i), emails.get(i)));
                }
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
            List<String> names;
            List<String> descriptions;
            List<String> prices;
            List<String> clinicVATNumbers;


            while(keys.hasNext()) {
                String code = keys.next();

                names = Arrays.asList(json.getJSONObject(code).getString("grouped_names").split(","));
                descriptions = Arrays.asList(json.getJSONObject(code).getString("grouped_descriptions").split(","));
                prices = Arrays.asList(json.getJSONObject(code).getString("grouped_prices").split(","));
                clinicVATNumbers = Arrays.asList(json.getJSONObject(code).getString("grouped_clinicVATNumbers").split(","));

                for(int i=0;i<names.size();i++) {
                    services.add(new Service(code, names.get(i), descriptions.get(i), Double.parseDouble(prices.get(i)), Integer.parseInt(clinicVATNumbers.get(i))));
                }
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
            List<String> times;
            List<String> dates;
            List<String> tos;
            List<String> accepted;

            while (keys.hasNext()) {
                String appointment = keys.next();
                times = Arrays.asList(json.getJSONObject(appointment).getString("grouped_times").split(","));
                dates = Arrays.asList(json.getJSONObject(appointment).getString("grouped_dates").split(","));
                tos = Arrays.asList(json.getJSONObject(appointment).getString("grouped_tos").split(","));
                accepted = Arrays.asList(json.getJSONObject(appointment).getString("grouped_accepted").split(","));

                for (int i = 0; i < accepted.size(); i++) {
                    appointments.add(new Appointment(times.get(i), dates.get(i), tos.get(i), Integer.parseInt(appointment), Boolean.parseBoolean(accepted.get(i))));
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

    public void deletePastAppointments(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
    }
}
