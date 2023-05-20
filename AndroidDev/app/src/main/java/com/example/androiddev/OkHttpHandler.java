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
            while(keys.hasNext()) {
                String patient_ssn = keys.next();
                dates = Arrays.asList(json.getJSONObject(patient_ssn).getString("grouped_date").split(","));
                tos = Arrays.asList(json.getJSONObject(patient_ssn).getString("grouped_tos").split(","));
                costs = Arrays.asList(json.getJSONObject(patient_ssn).getString("grouped_cost").split(","));
                for(int i=0; i<dates.size(); i++)
                {
                    movements.add(new Movement(dates.get(i), tos.get(i), Double.parseDouble(costs.get(i))));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movements;
    }


}
