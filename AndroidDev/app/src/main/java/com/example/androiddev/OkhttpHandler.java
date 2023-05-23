package com.example.androiddev;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpHandler {



    public OkhttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

//    public OkHttpClient customSSL() throws KeyManagementException, NoSuchAlgorithmException{
//        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            }
//
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            }
//
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//        }};
//
//        // Create a SSL context with the trust manager
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, trustAllCerts, new SecureRandom());
//
//        // Configure OkHttpClient to use the custom SSL context
//        OkHttpClient client = new OkHttpClient.Builder()
//                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
//                .hostnameVerifier((hostname, session) -> true)
//                .build();
//        return client;
//    }

    public void fetchData(ArrayList<Appointment> appointmentsList, String url) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        String appointments = "no data";


        // Create a trust manager that accepts all certificates


        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();

        //Fetching the data from the database
        OkHttpClient client = new OkHttpClient().newBuilder().build();
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
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                Response response = client.newCall(request).execute();
                response.body().close();
            }catch(IOException  e){
                e.printStackTrace();
            }



    }

    public void onDenyClicked(int appointmentID, String url){


        RequestBody requestBody = new FormBody.Builder().add("appointment_id", String.valueOf(appointmentID)).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        try{
            // deleting an appointment from the database.
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            Response response = client.newCall(request).execute();

            response.body().close();
        }catch(IOException  e){
            e.printStackTrace();
        }
    }


}