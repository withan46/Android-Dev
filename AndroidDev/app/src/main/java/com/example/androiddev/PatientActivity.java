package com.example.androiddev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class PatientActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity);
    }

    public void logOutLink(View view){
        Intent intent = new Intent(PatientActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void arrangeOnClick(View view){
        Intent intent = new Intent(PatientActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void economicOnClick(View view){
        Intent intent = new Intent(PatientActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void viewOnClick(View view){
        Intent intent = new Intent(PatientActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void doctorOnClick(View view){
        Intent intent = new Intent(PatientActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
