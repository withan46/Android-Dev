package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientHistory_R5 extends AppCompatActivity {

    private String myIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienthistory_r5);
        Objects.requireNonNull(getSupportActionBar()).hide();
        myIP = getIntent().getStringExtra("ip");

        // Switch from Patient History to Patient Contact
        TextView myTextView = findViewById(R.id.contactText);

        // Retrieve the Patient object that was clicked by the user
        Patient patient = getIntent().getParcelableExtra("patient");

        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(PatientHistory_R5.this, PatientContact_R5.class);
                // Pass the patient object to the intent
                intent.putExtra("patient", patient);
                startActivity(intent);
            }
        });

        // Initialize the TextView and ImageView widgets
        TextView nameTextView = findViewById(R.id.patientName);
        ImageView imageView = findViewById(R.id.patientPicture);

        // Set the TextView and ImageView widgets with the patient's data
        nameTextView.setText(patient.getName());
        imageView.setImageResource(patient.getImageResource());

        // Initialize ScrollView and linearLayout to display patient's History
        ScrollView scrollView = findViewById(R.id.patientHistory);
        LinearLayout linearLayout = scrollView.findViewById(R.id.historyLinearLayout);

        // Getting patient's history
        List<String> historyList = patient.getHistory();
        for (String history : historyList) {
            // Inflate the history card layout
            View cardView = getLayoutInflater().inflate(R.layout.activity_patient_history_card, linearLayout, false);

            // Initialize date and description TextViews
            TextView dateTextView = cardView.findViewById(R.id.date);
            TextView descriptionTextView = cardView.findViewById(R.id.description);

            dateTextView.setText(history);

            // Set an OnClickListener to show the date's description
            descriptionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start the PatientDetailsActivity
                    Intent intent = new Intent(PatientHistory_R5.this, PatientContact_R5.class);
                    //instead of PatientContact_R5, Savvas' class for description information

                    // Pass the patient object to the intent
                    intent.putExtra("patient", patient);

                    // Start the activity
                    startActivity(intent);
                }
            });

            // Add the card to the container
            linearLayout.addView(cardView);

        }
    }
}