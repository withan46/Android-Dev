package com.example.androiddev.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androiddev.MainClasses.Doctor;
import com.example.androiddev.MainActivity;
import com.example.androiddev.OkHttpHandler;
import com.example.androiddev.R;

import java.io.IOException;

public class DoctorActivityR1 extends Activity {

    private String ip;
    private Doctor doctor;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity);

        String name = getIntent().getStringExtra("name");
        String[] fullName = name.split(" ");
        String email = getIntent().getStringExtra("email");
        ip = getIntent().getStringExtra("Ip");
        String url = "http://"+ip+"/flexFitDBServices/doctorLandingPageR1.php?email="+email+"&first_name="+ fullName[0] + "&last_name=" + fullName[1];
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
        Intent intent = new Intent(DoctorActivityR1.this, AllPatientsR5.class);
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
