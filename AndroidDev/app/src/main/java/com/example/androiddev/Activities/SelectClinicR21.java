package com.example.androiddev.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddev.OkHttpHandler;
import com.example.androiddev.R;

public class SelectClinicR21 extends AppCompatActivity {

    private String myIP;
    private EditText nameTW;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_clinic_r21);
        Intent parameters = getIntent();
        myIP = parameters.getStringExtra("Ip");
        nameTW = (EditText) findViewById(R.id.search_name_tb);
    }

    public void searchClinic(View view) {
        String url= "http://"+myIP+"/flexFitDBServices/findClinicR2.php?name=" + nameTW.getText();
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            String clinic_vat_number = okHttpHandler.findClinic(url);
            if (!clinic_vat_number.equals(""))
            {
                Intent intent = new Intent(SelectClinicR21.this, CreateNewServiceR22.class);
                intent.putExtra("clinic_vat_number", clinic_vat_number);
                intent.putExtra("ip", myIP);
                Toast.makeText(getApplicationContext(), "Clinic selected successfully!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else
                Toast.makeText(getApplicationContext(), "Not a valid name!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
