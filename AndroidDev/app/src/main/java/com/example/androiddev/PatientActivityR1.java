package com.example.androiddev;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PatientActivityR1 extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity);

        String name = getIntent().getStringExtra("message");
        TextView helloTxt = findViewById(R.id.hello);
        helloTxt.setText("Hello " + name + " !");
    }

    public void logOutLink(View view){
        Intent intent = new Intent(PatientActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void arrangeOnClick(View view){
        Intent intent = new Intent(PatientActivityR1.this, MainActivity.class);
        startActivity(intent);
    }
    public void economicOnClick(View view){
        Intent intent = new Intent(PatientActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void viewOnClick(View view){
        Intent intent = new Intent(PatientActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

    public void doctorOnClick(View view){
        Intent intent = new Intent(PatientActivityR1.this, MainActivity.class);
        startActivity(intent);
    }

}
