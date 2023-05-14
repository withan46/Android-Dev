package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllPatients_R5 extends AppCompatActivity {
    private String myIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpatients_r5);
        Objects.requireNonNull(getSupportActionBar()).hide();
        myIP = getIntent().getStringExtra("ip");

        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("John Doe", "johndoe@example.com", "123-45-6789", "555-1234", "04/08/2023", "18:00", "Allergies"));
        patientList.add(new Patient("Jane Doe", "janedoe@example.com", "987-65-4321", "555-5678", "18/07/2023", "13:00", "Back pain"));
        patientList.add(new Patient("Mary Doe", "marydoe@example.com",  "987-65-4871", "555-5528", "19/10/2023", "17:00", "Arm pain"));

        // set text 'Patients' to SearchView on R5_1
        SearchView searchView = findViewById(R.id.patientsSearchView); // get reference to your SearchView
        String queryText = "Patients"; // replace with your desired text
        searchView.setQuery(queryText, false); // set the query text and submit flag (false)

        // Switch from R5_1_2 (Today's appointments) to R5_1 (all patients)
        TextView myTextView = findViewById(R.id.appointmentsTextView);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(AllPatients_R5.this, TodaysPatients_R5.class);
                startActivity(intent);
            }
        });

        ScrollView scrollView = findViewById(R.id.patientsScrollView);
        LinearLayout linearLayout = scrollView.findViewById(R.id.patientLinearLayout);

        // Create an instance of OkHttpHandler and call populateScrollView to fetch the patients data
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            patientList = okHttpHandler.populateScrollView("http://" + myIP + "/get_patients.php");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        for (Patient patient : patientList) {
            // Inflate the patient card layout
            View cardView = getLayoutInflater().inflate(R.layout.activity_patient_card, linearLayout, false);

            // Set the patient's information in the card
            TextView nameTextView = cardView.findViewById(R.id.patientName);
            TextView appointmentTextView = cardView.findViewById(R.id.nextAppointment);
            TextView caseTextView = cardView.findViewById(R.id.patientCase);

            nameTextView.setText(patient.getName());
            appointmentTextView.setText("Next appointment: " + patient.getNextAppointment() + " " + patient.getNextAppointmentTime());
            caseTextView.setText("Case: " + patient.getCase());

            // Set the patient's image in the card
            ImageView imageView = cardView.findViewById(R.id.patientImage);
            imageView.setImageResource(patient.getImageResource());

            // Set an OnClickListener to show the patient details (move to R5_3)
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start the PatientDetailsActivity
                    Intent intent = new Intent(AllPatients_R5.this, PatientContact_R5.class);

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