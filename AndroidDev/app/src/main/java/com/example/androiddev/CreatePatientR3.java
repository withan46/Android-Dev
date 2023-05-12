package com.example.androiddev;

import android.app.Activity;
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
    private String vatRegNum = "1000";
    private Button registerButton;
    private String myIP = "192.168.1.3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_patient);

    }

    public void createOnClick(View v){
        registerButton = (Button) findViewById(R.id.createPatientButton);

        name = ((EditText) findViewById(R.id.name_field)).getText().toString();
        email = ((EditText) findViewById(R.id.email_field)).getText().toString();
        phoneNumber = ((EditText) findViewById(R.id.phonenumber_field)).getText().toString();
        ssn = ((EditText) findViewById(R.id.ssn_field)).getText().toString();

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            boolean successfulRegister  = okHttpHandler.add_patient("http://" +myIP +"/Android_R3/r3.php?ssn=" +ssn +"&email=" +email
            +"&name=" +name +"&phone_number=" +phoneNumber +"&vat_reg_num=" +vatRegNum);

            if(successfulRegister)
                Toast.makeText(getApplicationContext(),"Successful register", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
