package com.example.androiddev.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddev.MainClasses.PatientHasHistory;
import com.example.androiddev.R;

public class PatientContactR5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientcontact_r5);

        // Switch from R5_1_2 (Today's appointments) to R5_1 (all patients)
        TextView myTextView = findViewById(R.id.historyText);

        //Retrieve the Patient object that was clicked by the user
        String ip = getIntent().getStringExtra("Ip");
        PatientHasHistory patient = getIntent().getParcelableExtra("patient");

        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(PatientContactR5.this, PatientHistoryR5.class);

                // Pass the patient object to the intent
                intent.putExtra("patient", patient);
                intent.putExtra("Ip", ip);

                // Start the activity
                startActivity(intent);
            }
        });

        // Initialize the TextView and ImageView widgets
        TextView nameTextView = findViewById(R.id.patientName);
        TextView phoneNumberTextView = findViewById(R.id.phoneNumberField);
        TextView emailTextView = findViewById(R.id.emailField);
        // TextView preparationMessageTextView = findViewById(R.id.preparationMessage);
        ImageView imageView = findViewById(R.id.patientPicture);


        // Set the TextView and ImageView widgets with the patient's data
        nameTextView.setText(patient.getName());
        //ageTextView.setText(patient.getAge().toString());
        phoneNumberTextView.setText(patient.getPhoneNumber());
        emailTextView.setText(patient.getEmail());
        imageView.setImageResource(patient.getImageResource());


    }
}