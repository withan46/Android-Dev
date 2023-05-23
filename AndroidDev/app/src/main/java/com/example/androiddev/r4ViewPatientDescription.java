package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class r4ViewPatientDescription extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r4_view_patient_description);

        //TextView variables declaration//
        TextView descriptionView = findViewById(R.id.textView_Description);
        TextView tosView = findViewById(R.id.textView_tos);
        TextView dateView = findViewById(R.id.textView_Date);
        TextView patientNameView = findViewById(R.id.textView_Name);
        //------------------------------//

        //Im getting this from the previous R and IP from main activity//
        String date = "2022-01-15";
        String time = "17:00 PM";
        String patientSSN = "111111111";
        String myIP = "192.168.2.4";
        String patientName = "John Doe";
        //-------------------------------------------------------------//

        History history = getPatientHistory(myIP, patientSSN, date, time);

        //Add values to the textViews//
        descriptionView.setText(history.getDescription());
        String tosString = "Service: " + history.getTos();
        tosView.setText(tosString);
        String dateTime = history.getTime() + " " + history.getDate();
        dateView.setText(dateTime);
        patientNameView.setText(patientName);
        //--------------------------//
    }

    private History getPatientHistory(String myIP, String patientSSN, String date, String time) {
        String url= "http://"+ myIP +"/flexFitDBServices/r4GetPatientHistory.php?patientSSN=" + patientSSN + "&date=" + date + "&time=" + time ;
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