package com.example.androiddev;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.IOException;

public class PatientActivityR1 extends Activity {

    private String Ip;
    private PatientR1 patient;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity);
        String name = getIntent().getStringExtra("message");
        String email = getIntent().getStringExtra("email");
        Ip = getIntent().getStringExtra("Ip");
        TextView helloTxt = findViewById(R.id.hello);
        helloTxt.setText("Hello " + name + " !");

        String url = "http://"+Ip+"/flexFitDBServices/patientLandingPage.php?email="+email+"&name="+name;
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            patient= okHttpHandler.getLoggedPatient(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOutLink(View view){
        Intent intent = new Intent(PatientActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void arrangeOnClick(View view){
        Intent intent = new Intent(PatientActivityR1.this, SearchClinicR9.class);
        intent.putExtra("Ip", Ip);
        startActivity(intent);
    }
    public void economicOnClick(View view){
        Intent intent = new Intent(PatientActivityR1.this, ShowEconomicMovementsR10.class);
        intent.putExtra("Ip", Ip);
        intent.putExtra("patient_ssn", patient.getSsn());
        startActivity(intent);
    }
}
