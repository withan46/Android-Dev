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

import java.util.*;

public class R8 extends AppCompatActivity {

    // IP of my Computer
    String myIP = "192.168.1.254";

    //Creating an arrayList of patients for testing.
    ArrayList<AcceptedAppointments> patients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Creating HTTP handler Object
        OkHttpHandler okHttpHandler = new OkHttpHandler();

        ////// I NEED TO TAKE THIS "10000" NUMBER FROM ANOTHER R //////
        String url1 = "http://"+ myIP +"/AndroidDev/getAcceptedAppointments.php?clinic_vat_number=" + 10000;

        try {
            patients = okHttpHandler.getAcceptedAppointments(url1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.r8layout);

        // Fetching the parentlayout
        LinearLayout patientBoxParent = findViewById(R.id.patientsParent);

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
                                 if ( (name.getText().toString()).equals(ap.getPatientName()) )
                                 {
                                     chosen = ap;   

                                     // URL that will be used to Access .php file in the Database
                                     String url3 = "http://"+myIP+"/AndroidDev/setHistory.php?patient_ssn=100&tos=%22%CE%91%CF%80%CE%BB%CE%AE%20%CE%A6%CF%85%CF%83%CE%B9%CE%BA%CE%BF%CE%B8%CE%B5%CF%81%CE%B1%CF%80%CE%B5%CE%AF%CE%B1%22&time=%2209:30:00%22&date=%222022-05-05%22&description=%22%CE%8C%CE%BB%CE%B1%20%CF%80%CE%AE%CE%B3%CE%B1%CE%BD%20%CE%BA%CE%B1%CE%BB%CE%AC%22";
                                     String url2 = "http://"+myIP+"/AndroidDev/setHistory.php?patient_ssn='"+chosen.getSsn()+"'&tos='"+chosen.getTypeOfService()+"'&time='"+chosen.getAppointmentTime()+"'&date='"+chosen.getAppointmentDate()+"'&description='"+descr+"'";

                                     OkHttpHandler okHttpHandler = new OkHttpHandler();
                                     System.out.println(chosen.getTypeOfService());
                                     try {
                                         okHttpHandler.addHistory(url2);
                                     } catch (Exception e) {
                                         throw new RuntimeException();
                                     }

                                 }
                             }


                             Toast.makeText(R8.this, "Description Added", Toast.LENGTH_SHORT).show();

                             // Getting the patientBox layout (Buttons are each inside a relative layout, which is inside a linear layout, which is inside the patientBox)
                             LinearLayout parent = (LinearLayout) addButton.getParent();

                             //Making the patientBox Invisible
                             parent.setVisibility(View.GONE);

                         }

                     });
                 }
             });
         }
    }
}