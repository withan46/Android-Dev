package com.example.androiddev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class RegisterClinicR1 extends Activity {

    private String myIP;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_clinic);

        Intent intent = getIntent();
        this.myIP = intent.getStringExtra("Ip");

        TextView vatNumberTextView = (TextView) findViewById(R.id.vatNumberTxt);
        TextView nameTextView = (TextView) findViewById(R.id.nameTxt);
        TextView emailTextView = (TextView) findViewById(R.id.emailTxt);
        TextView phoneTextView = (TextView) findViewById(R.id.phoneNumberTxt);
        TextView addressTextView = (TextView) findViewById(R.id.addressTxt);

        Button insertDataButton = (Button) findViewById(R.id.createButton);
        insertDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get values from text views
                if (TextUtils.isEmpty(vatNumberTextView.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }else {
                    int vatNumber = Integer.parseInt(vatNumberTextView.getText().toString());
                    String name = nameTextView.getText().toString();
                    String email = emailTextView.getText().toString();
                    String phone = phoneTextView.getText().toString();
                    String address = addressTextView.getText().toString();


                    if (checkInput(vatNumber, email, phone)) {
                        String url = "http://" + myIP + "/flexfitDBServices/clinic_data.php?vat_number=" + vatNumber +
                                "&name=" + name + "&address=" + address + "&phone_number=" + phone + "&email=" + email;
                        try {
                            OkHttpHandler okHttpHandler = new OkHttpHandler();
                            okHttpHandler.clinic_data(url);
                            Toast.makeText(getApplicationContext(), "Selection Logged",
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid Vat Number", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public boolean checkInput(Integer vatNumber, String email, String phone){
        boolean isInputCorrect= true;
        if(!(String.valueOf(vatNumber).length() == 5)){
            Toast.makeText(getApplicationContext(), "Invalid Vat Number", Toast.LENGTH_SHORT).show();
            isInputCorrect = false;
        }else if(!(phone.length() == 10)){
            Toast.makeText(getApplicationContext(), "Invalid phone Number", Toast.LENGTH_SHORT).show();
            isInputCorrect = false;
        }else if(!(email.contains("@clinic.com"))){
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            isInputCorrect = false;
        }

        return isInputCorrect;
    }



}
