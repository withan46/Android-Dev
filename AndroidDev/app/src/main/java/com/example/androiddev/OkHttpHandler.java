package com.example.androiddev;

import android.os.*;
import android.widget.Toast;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.*;

import java.security.SecureRandom;
import java.util.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
        if (response.isSuccessful()) {
            data = response.body().string();
            try {
                JSONObject json = new JSONObject(data);
                Iterator<String> keys = json.keys();
                while (keys.hasNext()) {
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
            while (keys.hasNext()) {
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
                if (!jsonObject.isNull("history_date")) {
                    historyDate = jsonObject.getString(("history_date"));
                } else {
                    historyDate = "";
                }
                List<String> history = new ArrayList<>();
                String historyTime;
                if (!jsonObject.isNull("history_time")) {
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

    public ArrayList<weeklyPlannerDataR6> populateWeeklyPlanner(String url) throws IOException {
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

            while (keys.hasNext()) {
                String date = keys.next();
                times = Arrays.asList(json.getJSONObject(date).getString("grouped_times").split(","));
                tos = Arrays.asList(json.getJSONObject(date).getString("grouped_tos").split(","));
                names = Arrays.asList(json.getJSONObject(date).getString("grouped_names").split(","));
                for (int i = 0; i < times.size(); i++) {
                    weeklyPlannerDatumR6s.add(new weeklyPlannerDataR6(times.get(i), tos.get(i), names.get(i)));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weeklyPlannerDatumR6s;
    }

    public OkHttpClient customSSL() throws KeyManagementException, NoSuchAlgorithmException{
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        // Create a SSL context with the trust manager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        // Configure OkHttpClient to use the custom SSL context
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier((hostname, session) -> true)
                .build();
        return client;
    }

    public void fetchData(ArrayList<Appointment> appointmentsList, String url) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        String appointments = "no data";


        // Create a trust manager that accepts all certificates


        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();

        //Fetching the data from the database
        OkHttpClient client = customSSL();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful())
            appointments = response.body().string();
        try{
            //Storing every appointment's information in variables.
            JSONArray jsonAppointments = new JSONArray(appointments);
            for(int i =0 ; i < jsonAppointments.length() ; i ++){
                JSONObject patientInformation = jsonAppointments.getJSONObject(i);
                String patientSSN = patientInformation.getString("ssn");
                String patientName = patientInformation.getString("name");
                String appointmentDate = patientInformation.getString("date");
                String appointmentTime = patientInformation.getString("time");
                String appointmentNote = patientInformation.getString("tos");
                int appointmentID = patientInformation.getInt("id");
                //Creating a new appointment object, based on the information of the current appointment.
                Appointment appointment = new Appointment(patientSSN, patientName, appointmentDate, appointmentTime, appointmentNote,appointmentID);
                //Adding the appointment to the appointments' list.
                appointmentsList.add(appointment);


            }
            response.body().close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void onAcceptClicked(int appointmentID, String url){


        RequestBody requestBody = new FormBody.Builder().add("appointment_id", String.valueOf(appointmentID)).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        try{
            // changing the state of an appointment from false to true.
            OkHttpClient client = customSSL();
            Response response = client.newCall(request).execute();
            response.body().close();
        }catch(IOException  e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }


    }

    public void onDenyClicked(int appointmentID, String url){


        RequestBody requestBody = new FormBody.Builder().add("appointment_id", String.valueOf(appointmentID)).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        try{
            // deleting an appointment from the database.
            OkHttpClient client = customSSL();

            Response response = client.newCall(request).execute();

            response.body().close();
        }catch(IOException  e){
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
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
