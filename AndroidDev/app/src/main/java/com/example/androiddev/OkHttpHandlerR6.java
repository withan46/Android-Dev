package com.example.androiddev;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandlerR6 {
    public OkHttpHandlerR6() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
