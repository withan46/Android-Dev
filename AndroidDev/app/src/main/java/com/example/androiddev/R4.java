package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class R4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r4);

        //TextView variables declaration//
        TextView descriptionView = findViewById(R.id.descriptionTextView);
        TextView tosView = findViewById(R.id.textView_tos);
        TextView dateView = findViewById(R.id.textView_Date);
        TextView patientNameView = findViewById(R.id.textView_Name);
        //------------------------------//

        //Im getting this from the previous R and IP from main activity//
        String date = "2022-01-15";
        String time = "17:00 PM";
        String patientSSN = "111111111";
        String myIP = "192.168.1.5";
        String patientName = "John Doe";
        //-------------------------------------------------------------//

        History history = getPatientHistory(myIP, patientSSN, date, time);

        //Add values to the textViews//
        descriptionView.setText(history.getDescription());
        System.out.println(history.getDescription());
        tosView.setText(history.getTos());
        String dateTime = history.getTime() + " " + history.getDate();
        dateView.setText(dateTime);
        patientNameView.setText(patientName);
        //--------------------------//
    }

    private History getPatientHistory(String myIP, String patientSSN, String date, String time) {
        String url= "http://"+ myIP +"/r4.php?patientSSN=" + patientSSN + "&date=" + date + "&time=" + time ;
        History history = null;
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            history = okHttpHandler.getPatientHistory(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return history;
    }
}