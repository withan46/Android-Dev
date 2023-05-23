package com.example.androiddev;

import android.os.*;
import android.util.Log;

import org.json.*;
import java.util.*;
import okhttp3.*;

public class OkHttpHandlerR1 {

    public OkHttpHandlerR1() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        //System.out.println("My Response: " + data);
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                String email = json.getString("email");
                String password = json.getString("password");
                String name = json.getString("name");

                Log.d("Data", "Email: " + email);
                Log.d("Data", "Password: " + password);
                Log.d("Data", "Name: " + name);

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
        System.out.println("My Response: " + response);
    }

}