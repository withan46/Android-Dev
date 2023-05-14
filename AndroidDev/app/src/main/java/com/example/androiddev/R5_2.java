package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class R5_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r52);

        // Switch from R5_1_2 (Today's appointments) to R5_1 (all patients)
        TextView myTextView = findViewById(R.id.contactText);

        //Retrieve the Patient object that was clicked by the user
        Patient patient = getIntent().getParcelableExtra("patient");

        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(R5_2.this, R5_3.class);

                // Pass the patient object to the intent
                intent.putExtra("patient", patient);

                startActivity(intent);
            }
        });

        // Initialize the TextView and ImageView widgets
        TextView nameTextView = findViewById(R.id.patientName);
        TextView ageTextView = findViewById(R.id.patientAge);;
        // TextView preparationMessageTextView = findViewById(R.id.preparationMessage);
        ImageView imageView = findViewById(R.id.patientPicture);


        // Set the TextView and ImageView widgets with the patient's data
        nameTextView.setText(patient.getName());
        //ageTextView.setText(patient.getAge().toString());
        //preparationMessageTextView.setText(patient.getPhoneNumber());
        imageView.setImageResource(patient.getImageResource());
    }
}