package com.example.androiddev.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androiddev.MainClasses.History;
import com.example.androiddev.OkHttpHandler;
import com.example.androiddev.MainClasses.PatientHasHistory;
import com.example.androiddev.R;

public class ViewPatientDescriptionR4 extends AppCompatActivity {
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
        Intent intent = getIntent();
        String myIP = intent.getStringExtra("Ip");
        PatientHasHistory patient = intent.getParcelableExtra("patient");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String patientSSN = patient.getSsn();
        String patientName = patient.getName();
        //-------------------------------------------------------------//



        History history = getPatientHistory(myIP, patientSSN, date, time);
        patientNameView.setText(patientName);
        //Add values to the textViews//
        if(history != null) {
            descriptionView.setText(history.getDescription());
            String tosString = "Service: " + history.getTos();
            tosView.setText(tosString);
            String dateTime = history.getTime() + " " + history.getDate();
            dateView.setText(dateTime);
        }
        //--------------------------//
    }

    private History getPatientHistory(String myIP, String patientSSN, String date, String time) {
        String url= "http://"+ myIP +"/flexFitDBServices/getPatientHistoryR4.php?patientSSN=" + patientSSN + "&date=" + date + "&time=" + time ;
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