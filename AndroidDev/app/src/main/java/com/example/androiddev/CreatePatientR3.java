package com.example.androiddev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePatientR3 extends Activity {

    private String name;
    private String email;
    private String phoneNumber;
    private String ssn;
    private String vatRegNum = "1000"; //this is a dummy value! the real value will be taken by the landing page of the doctor...
    private Button registerButton;
    private String myIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_patient_r3);

        Intent parameters = getIntent();
        myIP = parameters.getStringExtra("ip");

    }

    public void createOnClick(View v){

        registerButton = (Button) findViewById(R.id.createPatientButton);

        //get the content of the required fields
        name = ((EditText) findViewById(R.id.name_field)).getText().toString();
        email = ((EditText) findViewById(R.id.email_field)).getText().toString();
        phoneNumber = ((EditText) findViewById(R.id.phonenumber_field)).getText().toString();
        ssn = ((EditText) findViewById(R.id.ssn_field)).getText().toString();

        //check if all the fields are completed. if not, then a warning Toast appears and the data is NOT written to the database
        if(name.equals("") || email.equals("") || phoneNumber.equals("") || ssn.equals(""))
            Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
        else {
            OkHttpHandler okHttpHandler = new OkHttpHandler();

            try {
                //add the patient's data to the database, through the PHP file
                boolean successfulRegister = okHttpHandler.add_patient("http://" + myIP + "/Android_R3/createPatientR3.php?ssn=" + ssn + "&email=" + email
                        + "&name=" + name + "&phone_number=" + phoneNumber + "&vat_reg_num=" + vatRegNum);

                if (successfulRegister) { //if the registration has been successful, then a relative Toast is been shown
                    Toast.makeText(getApplicationContext(), "Successful register!", Toast.LENGTH_SHORT).show();

                    //clear the fields for a new registration
                    ((EditText) findViewById(R.id.name_field)).getText().clear();
                    ((EditText) findViewById(R.id.email_field)).getText().clear();
                    ((EditText) findViewById(R.id.phonenumber_field)).getText().clear();
                    ((EditText) findViewById(R.id.ssn_field)).getText().clear();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
