package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class TodaysPatients_R5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todayspatients_r5);
        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView myTextView = findViewById(R.id.allPatientsButton);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(TodaysPatients_R5.this, AllPatients_R5.class);
                startActivity(intent);
            }
        });
    }

    public void goToAppointments(View view) {
        Intent intent = new Intent(this, TodaysPatients_R5.class);
        startActivity(intent);
    }
    public void goToAll(View view) {
        Intent intent = new Intent(this, AllPatients_R5.class);
        startActivity(intent);
    }
}