package com.example.androiddev;

import android.os.StrictMode;

import okhttp3.OkHttpClient;
import okhttp3.*;
import okhttp3.RequestBody;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String addDescription(String url) throws Exception {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("",
                MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST",
                body).build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

}
