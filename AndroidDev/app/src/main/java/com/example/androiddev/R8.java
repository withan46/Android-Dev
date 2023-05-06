package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.*;

public class R8 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creating an arrayList of patients for testing.


        ArrayList<PatientInfo> patients = new ArrayList<>();
        // Creating three patients.
        PatientInfo pat1 = new PatientInfo("Domas Irena", "13.5.22", "13.45");
        PatientInfo pat2 = new PatientInfo("Katrin Angharad", "17.5.22", "13.00");
        PatientInfo pat3 = new PatientInfo("Joachim Mitsuaki", "17.5.22", "16.30");

        //Adding patients to the array list.
        patients.add(pat1);
        patients.add(pat2);
        patients.add(pat3);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r8layout);

        // Fetching the parentlayout
        LinearLayout patientBoxParent = findViewById(R.id.patientsParent);

         for(int i = 0 ; i < patients.size() ; i++){

             // fetching the layout that contains the patient's information
             LayoutInflater inflater = LayoutInflater.from(this);
             LinearLayout patientBox = (LinearLayout) inflater.inflate(R.layout.patientboxlayout, patientBoxParent, false);




             // fetching the elements that vary and change dynamically based on the patient.
             TextView name = patientBox.findViewById(R.id.patientName);
             TextView date = patientBox.findViewById(R.id.appDate);
             TextView time = patientBox.findViewById(R.id.appTime);

             Button saveButton = patientBox.findViewById(R.id.saveButton);
             TextInputEditText addText = patientBox.findViewById(R.id.addText);

             saveButton.setVisibility(View.GONE);
             addText.setVisibility(View.GONE);

             // getting all the necessary data and adding it to the appropriate elements.
             PatientInfo aPatient = patients.get(i);
             name.setText(aPatient.getPatientName());
             date.setText(aPatient.getAppointmentDate());
             time.setText(aPatient.getAppointmentTime());


             // Adding the layout containing a patient's information to its parent layout.

             patientBoxParent.addView(patientBox);

             //Getting the accept and deny appointment buttons, in order to add onClickListeners.
             Button addButton = patientBox.findViewById(R.id.addButton);



             addButton.setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View v){

                     // Making "AddDescription" Button disappear
                     addButton.setVisibility(View.GONE);

                     // Making the Text Area and "Save Button" Visible
                     saveButton.setVisibility(View.VISIBLE);
                     addText.setVisibility(View.VISIBLE);

                     saveButton.setOnClickListener(new View.OnClickListener(){

                         public void onClick (View v) {

                             // Getting String from Text Area
                             String descr = addText.getText().toString();
                             Toast.makeText(R8.this, "Description Saved", Toast.LENGTH_SHORT).show();

                             // Getting the patientBox layout (Buttons are each inside a relative layout, which is inside a linear layout, which is inside the patientBox)
                             LinearLayout parent = (LinearLayout) addButton.getParent();
                             //Removing the patientBox
                             parent.setVisibility(View.GONE);

                         }
                     });
                 }
             });
         }

    }
    
}