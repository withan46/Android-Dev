package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectClinic extends AppCompatActivity {

    private final String myIP = "192.168.178.86";
    private EditText nameTW;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_clinic);
        nameTW = (EditText) findViewById(R.id.search_name_tb);
    }

    public void searchClinic(View view) {
        String url= "http://"+myIP+"/FlexFit/findClinic.php?name=" + nameTW.getText();
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            String clinic_vat_number = okHttpHandler.findClinic(url);
            if (!clinic_vat_number.equals(""))
            {
                Intent intent = new Intent(SelectClinic.this, CreateNewService.class);
                intent.putExtra("clinic_vat_number", clinic_vat_number);
                startActivity(intent);
            }
            else
                Toast.makeText(getApplicationContext(), "Not a valid name!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
