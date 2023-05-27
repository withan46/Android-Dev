package com.example.androiddev.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.androiddev.MainClasses.PatientHasHistory;
import com.example.androiddev.R;

import java.util.List;

public class PatientHistoryR5 extends AppCompatActivity {

    private String myIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienthistory_r5);
        myIP = getIntent().getStringExtra("Ip");
        // Switch from Patient History to Patient Contact
        TextView myTextView = findViewById(R.id.contactText);

        // Retrieve the Patient object that was clicked by the user
        PatientHasHistory patient = getIntent().getParcelableExtra("patient");

        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(PatientHistoryR5.this, PatientContactR5.class);
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
                    Intent intent = new Intent(PatientHistoryR5.this, ViewPatientDescriptionR4.class);
                    //instead of PatientContact_R5, Savvas' class for description information
                    String[] date = dateTextView.getText().toString().split(" ");
                    // Pass the patient object to the intent
                    intent.putExtra("Ip", myIP);
                    intent.putExtra("patient", patient);
                    intent.putExtra("date", date[0]);
                    intent.putExtra("time", date[1]+ " " + date[2]);


                    // Start the activity
                    startActivity(intent);
                }
            });

            // Add the card to the container
            linearLayout.addView(cardView);

        }
    }
}