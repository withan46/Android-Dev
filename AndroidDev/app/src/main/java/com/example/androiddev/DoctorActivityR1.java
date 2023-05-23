package com.example.androiddev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.IOException;

public class DoctorActivityR1 extends Activity {

    private String ip;
    private Doctor doctor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity);

        String name = getIntent().getStringExtra("message");
        String[] fullName = name.split(" ");
        String email = getIntent().getStringExtra("email");
        ip = getIntent().getStringExtra("Ip");
        String url = "http://"+ip+"/flexFitDBServices/doctorLandingPage.php?email="+email+"&first_name="+ fullName[0] + "&last_name=" + fullName[1];
        OkHttpHandler okHttpHandler = new OkHttpHandler();

        try {
            doctor = okHttpHandler.getLoggedDoctor(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TextView helloTxt = findViewById(R.id.hello);
        helloTxt.setText("Hello " + name + " !");
    }

    public void logOutLink(View view){
        Intent intent = new Intent(DoctorActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void manageOnClick(View view){
        Intent intent = new Intent(DoctorActivityR1.this, ViewAndManageAppointmentRequestsR7.class);
        intent.putExtra("Ip", ip);
        intent.putExtra("clinic_vat_number", doctor.getClinicVatNumber());
        startActivity(intent);
    }
    public void viewPatientOnClick(View view){
        Intent intent = new Intent(DoctorActivityR1.this, AllPatients_R5.class);
        intent.putExtra("Ip", ip);
        intent.putExtra("vat_reg_num", doctor.getVatRegNum());
        startActivity(intent);
    }

    public void viewAppointmentsOnClick(View view){
        Intent intent = new Intent(DoctorActivityR1.this, WeeklyPlannerR6.class);
        intent.putExtra("Ip", ip);
        intent.putExtra("clinic_vat_number", doctor.getClinicVatNumber());
        startActivity(intent);
    }

    public void patientOnClick(View view){
        Intent intent = new Intent(DoctorActivityR1.this, CreatePatientR3.class);
        intent.putExtra("Ip", ip);
        intent.putExtra("vat_reg_num", doctor.getVatRegNum());
        startActivity(intent);
    }
}
