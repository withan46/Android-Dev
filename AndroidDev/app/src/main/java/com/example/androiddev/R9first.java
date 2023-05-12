package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class R9first extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r9);

        OkHttpHandler httpHandler = new OkHttpHandler();

        Button searchButton = findViewById(R.id.search_button);
        spinner = findViewById(R.id.spinner);

        List<String> clinicNames = new ArrayList<>();
        List<Clinic> clinics;

        try {
            String myIP = "192.168.1.5";
            clinics = httpHandler.populateClinicDropDown("http://" + myIP + "/r9First.php");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clinicNames.add("");
        for(Clinic clinic : clinics) {
            clinicNames.add(clinic.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clinicNames);
        spinner.setAdapter(arrayAdapter);

        spinner = findViewById(R.id.spinner);
        spinner.setSelection(0, false);

        // Set the default placeholder text for the spinner
        TextView placeholderText = findViewById(R.id.placeholder_text);

        // Set an onItemSelectedListener to update the placeholder text when an item is selected
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(id == 0) {
                    spinner.setSelection(0, false);
                    placeholderText.setVisibility(View.VISIBLE);
                    return;
                }
                placeholderText.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                placeholderText.setVisibility(View.VISIBLE);
            }
        });

        searchButton.setOnClickListener(v -> {
            long selectedClinic = spinner.getSelectedItemId();
            if(selectedClinic != 0) {
                String selectedVATNumber = Integer.toString(clinics.get((int) selectedClinic-1).getVatNumber());
                Intent intent = new Intent(this, R9second.class);

                intent.putExtra("clinicVAT", selectedVATNumber);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "You have to pick a clinic first!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}