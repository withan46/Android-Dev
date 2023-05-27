package com.example.androiddev.Activities;

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

import com.example.androiddev.MainClasses.Clinic;
import com.example.androiddev.MainClasses.Patient;
import com.example.androiddev.OkHttpHandler;
import com.example.androiddev.R;

import java.util.ArrayList;
import java.util.List;

public class SearchClinicR9 extends AppCompatActivity {

    private Spinner spinner;
    private String myIP;
    private Patient patient;
    private String patientSSN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r9_search_clinic);

        OkHttpHandler httpHandler = new OkHttpHandler();

        //Will be received from previous R and IP from Main activity//
        Intent intent = getIntent();
        this.myIP = intent.getStringExtra("Ip");
        this.patient = intent.getParcelableExtra("patient");
        this.patientSSN = this.patient.getSsn();
        //----------------------------------------------------------//

        Button searchButton = findViewById(R.id.search_button);
        spinner = findViewById(R.id.spinner);

        //Filling clinics dropdown with clinics names//
        List<String> clinicNames = new ArrayList<>();
        List<Clinic> clinics;

        try {
            clinics = httpHandler.populateClinicDropDown("http://" + myIP + "/flexFitDBServices/searchClinicR9.php");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clinicNames.add("");
        for(Clinic clinic : clinics) {
            clinicNames.add(clinic.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clinicNames);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0, false);

        //-Set the default placeholder text for the spinner-//
        TextView placeholderText = findViewById(R.id.placeholder_text);
        //-------------------------------------------//

        //-Set an onItemSelectedListener to update the placeholder text when an item is selected-//
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

        //--Search Button event listener--//
        searchButton.setOnClickListener(v -> {
            long selectedClinic = spinner.getSelectedItemId();
            if(selectedClinic != 0) {
                String selectedVATNumber = Integer.toString(clinics.get((int) selectedClinic-1).getVatNumber());
                Intent arrangeAppointment = new Intent(this, BookAppointmentR9.class);

                arrangeAppointment.putExtra("Ip", myIP);
                arrangeAppointment.putExtra("clinicVAT", selectedVATNumber);
                arrangeAppointment.putExtra("patient", patient);

                startActivity(arrangeAppointment);
            }
            else {
                Toast.makeText(getApplicationContext(), "You have to pick a clinic first!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}