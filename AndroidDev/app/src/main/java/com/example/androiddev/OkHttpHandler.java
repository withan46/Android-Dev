package com.example.androiddev;

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

        if(data != "[]"){
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

        return null;
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
                List<String> history = new ArrayList<>();
                if (!jsonObject.isNull("history")) {
                    JSONArray historyArray = jsonObject.getJSONArray("history");
                    for (int j = 0; j < historyArray.length(); j++) {
                        JSONObject historyObject = historyArray.getJSONObject(j);
                        String historyDate;
                        if (!historyObject.isNull("date")) {
                            historyDate = historyObject.getString("date");
                        } else {
                            historyDate = "";
                        }
                        String historyTime;
                        if (!historyObject.isNull("time")) {
                            historyTime = historyObject.getString("time");
                            String historyDateTime = historyDate + " " + historyTime;
                            history.add(historyDateTime);
                        }
                    }
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

    public OkHttpClient customSSL() throws KeyManagementException, NoSuchAlgorithmException {
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
        try {
            //Storing every appointment's information in variables.
            JSONArray jsonAppointments = new JSONArray(appointments);
            for (int i = 0; i < jsonAppointments.length(); i++) {
                JSONObject patientInformation = jsonAppointments.getJSONObject(i);
                String patientSSN = patientInformation.getString("ssn");
                String patientName = patientInformation.getString("name");
                String appointmentDate = patientInformation.getString("date");
                String appointmentTime = patientInformation.getString("time");
                String appointmentNote = patientInformation.getString("tos");
                int appointmentID = patientInformation.getInt("id");
                //Creating a new appointment object, based on the information of the current appointment.
                Appointment appointment = new Appointment(patientSSN, patientName, appointmentDate, appointmentTime, appointmentNote, appointmentID);
                //Adding the appointment to the appointments' list.
                appointmentsList.add(appointment);


            }
            response.body().close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onAcceptClicked(int appointmentID, String url) {


        RequestBody requestBody = new FormBody.Builder().add("appointment_id", String.valueOf(appointmentID)).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        try {
            // changing the state of an appointment from false to true.
            OkHttpClient client = customSSL();
            Response response = client.newCall(request).execute();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }


    }

    public void onDenyClicked(int appointmentID, String url) {


        RequestBody requestBody = new FormBody.Builder().add("appointment_id", String.valueOf(appointmentID)).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();

        try {
            // deleting an appointment from the database.
            OkHttpClient client = customSSL();

            Response response = client.newCall(request).execute();

            response.body().close();
        } catch (IOException e) {
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

            while (keys.hasNext()) {
                String clinic_vat_number = keys.next();
                times = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_times").toString().split(","));
                dates = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_dates").toString().split(","));
                ssn = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_ssn").toString().split(","));
                names = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_names").toString().split(","));
                tos = Arrays.asList(json.getJSONObject(clinic_vat_number).getString("grouped_tos").toString().split(","));
            }

            for (int i = 0; i < ssn.size(); i++) {
                aa.add(new AcceptedAppointments(names.get(i), ssn.get(i), dates.get(i), times.get(i), tos.get(i), ""));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aa;
    }

    public void addHistory(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
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

            while (keys.hasNext()) {
                String vatNumber = keys.next();

                names = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_names").split(","));
                addresses = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_addresses").split(","));
                phoneNumbers = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_numbers").split(","));
                emails = Arrays.asList(json.getJSONObject(vatNumber).getString("grouped_emails").split(","));

                for (int i = 0; i < names.size(); i++) {
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


            while (keys.hasNext()) {
                String code = keys.next();

                names = Arrays.asList(json.getJSONObject(code).getString("grouped_names").split(","));
                descriptions = Arrays.asList(json.getJSONObject(code).getString("grouped_descriptions").split(","));
                prices = Arrays.asList(json.getJSONObject(code).getString("grouped_prices").split(","));
                clinicVATNumbers = Arrays.asList(json.getJSONObject(code).getString("grouped_clinicVATNumbers").split(","));

                for (int i = 0; i < names.size(); i++) {
                    services.add(new Service(code, names.get(i), descriptions.get(i), Double.parseDouble(prices.get(i)), Integer.parseInt(clinicVATNumbers.get(i))));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return services;
    }

    public ArrayList<AppointmentR9> getClinicAppointments(String url) throws Exception {

        ArrayList<AppointmentR9> appointments = new ArrayList<>();

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
                    appointments.add(new AppointmentR9(times.get(i), dates.get(i), tos.get(i), Integer.parseInt(appointment), Boolean.parseBoolean(accepted.get(i))));
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

    public ArrayList<Movement> populateEconimicMovements(String url) throws Exception {

        ArrayList<Movement> movements = new ArrayList<>();

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
            List<String> dates, tos, costs;
            while (keys.hasNext()) {
                String patient_ssn = keys.next();
                dates = Arrays.asList(json.getJSONObject(patient_ssn).getString("grouped_date").split(","));
                tos = Arrays.asList(json.getJSONObject(patient_ssn).getString("grouped_tos").split(","));
                costs = Arrays.asList(json.getJSONObject(patient_ssn).getString("grouped_cost").split(","));
                for (int i = 0; i < dates.size(); i++) {
                    movements.add(new Movement(dates.get(i), tos.get(i), Double.parseDouble(costs.get(i))));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movements;
    }


    ArrayList<UsersR1> patient_data(String url) throws Exception {
        ArrayList<UsersR1> userList = new ArrayList<>();
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
                JSONObject json = jsonArray.getJSONObject(i);
                String email = json.getString("email");
                String password = json.getString("password");
                String name = json.getString("name");
                userList.add(new UsersR1(email, password, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userList;

    }

    public void clinic_data(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
    }

    public PatientR1 getLoggedPatient(String url) throws IOException {
        PatientR1 patient = null;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            String patientSSN = "";
            String email = "";
            String name = "";
            String phoneNumber = "";
            String vatRegNum = "";
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                patientSSN = json.getString("ssn");
                email = json.getString("email");
                phoneNumber = json.getString("phone_number");
                vatRegNum = json.getString("vat_reg_number");
            }
            patient = new PatientR1(patientSSN, email, name, phoneNumber, vatRegNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public Doctor getLoggedDoctor(String url) throws IOException {
        Doctor doctor = null;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {
            String vatRegNum = "";
            String name = "";
            String surname = "";
            String email = "";
            String clinicVatNumber = "";
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                vatRegNum = json.getString("vat_reg_number");
                name = json.getString("name");
                surname = json.getString("surname");
                email = json.getString("email");
                clinicVatNumber = json.getString("clinic_vat_number");
            }
            doctor = new Doctor(vatRegNum, name, surname, email, clinicVatNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
