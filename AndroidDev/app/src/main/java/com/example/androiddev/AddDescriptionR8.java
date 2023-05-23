package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.*;

public class AddDescriptionR8 extends AppCompatActivity {

    private String myIP;
    private String clinicVATNumber;

    //Creating an arrayList of patients for testing.
    ArrayList<AcceptedAppointments> patients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        this.myIP = intent.getStringExtra("Ip");
        this.clinicVATNumber = intent.getStringExtra("clinic_vat_number");

        // Creating HTTP handler Object
        OkHttpHandler okHttpHandler = new OkHttpHandler();

        String url1 = "http://"+ this.myIP +"/flexFitDBServices/getAcceptedAppointmentsR8.php?clinic_vat_number=" + this.clinicVATNumber;

        try {
            patients = okHttpHandler.getAcceptedAppointments(url1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_description_r8);

        // Fetching the parentlayout
        LinearLayout patientBoxParent = findViewById(R.id.patientsParent);

        LinearLayout buttonContainer = findViewById(R.id.buttonContainer2);
        Button requestButton = (Button) buttonContainer.getChildAt(0);

         for(int i = 0 ; i < patients.size() ; i++){

             // fetching the layout that contains the patient's information
             LayoutInflater inflater = LayoutInflater.from(this);
             LinearLayout patientBox = (LinearLayout) inflater.inflate(R.layout.patientboxlayout, patientBoxParent, false);


             // Fetching the elements that vary and change dynamically based on the patient.
             TextView name = patientBox.findViewById(R.id.patientName);
             TextView date = patientBox.findViewById(R.id.appDate);
             TextView time = patientBox.findViewById(R.id.appTime);
             TextView note = patientBox.findViewById(R.id.note);

             Button saveButton = patientBox.findViewById(R.id.saveButton);
             TextInputEditText addText = patientBox.findViewById(R.id.addText);

             saveButton.setVisibility(View.GONE);
             addText.setVisibility(View.GONE);

             // Getting all the necessary data and adding it to the appropriate elements.
             AcceptedAppointments aPatient = patients.get(i);
             name.setText(aPatient.getPatientName());
             date.setText(aPatient.getAppointmentDate());
             time.setText(aPatient.getAppointmentTime());
             note.setText("Service: "+aPatient.getTypeOfService());


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

                             String descr = "";
                             TextView name = patientBox.findViewById(R.id.patientName);
                             TextView time = patientBox.findViewById(R.id.appTime);
                             TextView date = patientBox.findViewById(R.id.appDate);

                             AcceptedAppointments chosen = null;

                             // Getting String from Text Area
                             if (!addText.getText().equals(null))
                             {
                                 descr = addText.getText().toString();
                             }

                             // Looking for the Patient whose Data and Description (History) we want to add to the Database
                             for(AcceptedAppointments ap: patients)
                             {
                                 // If found, we set the chosen Object = to the one that was found
                                 if ( (name.getText().toString()).equals(ap.getPatientName()) && time.getText().toString().equals(ap.getAppointmentTime()) && date.getText().toString().equals(ap.getAppointmentDate()))
                                 {
                                     chosen = ap;

                                     // URL that will be used to Access .php file in the Database
                                     String url2 = "http://"+myIP+"/flexFitDBServices/setHistoryR8.php?patient_ssn='"+chosen.getSsn()+"'&tos='"+chosen.getTypeOfService()+"'&time='"+chosen.getAppointmentTime()+"'&date='"+chosen.getAppointmentDate()+"'&description='"+descr+"'";

                                     OkHttpHandler okHttpHandler = new OkHttpHandler();
                                     try {
                                         okHttpHandler.addHistory(url2);
                                     } catch (Exception e) {
                                         throw new RuntimeException();
                                     }

                                 }
                             }


                             Toast.makeText(AddDescriptionR8.this, "Description Added", Toast.LENGTH_SHORT).show();

                             // Getting the patientBox layout (Buttons are each inside a relative layout, which is inside a linear layout, which is inside the patientBox)
                             LinearLayout parent = (LinearLayout) addButton.getParent();

                             //Making the patientBox Invisible
                             parent.setVisibility(View.GONE);

                         }

                     });
                 }
             });
         }

        requestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddDescriptionR8.this, ViewAndManageAppointmentRequestsR7.class);
                intent.putExtra("Ip", myIP);
                intent.putExtra("clinic_vat_number", clinicVATNumber);

                startActivity(intent);
            }
        });
    }
}