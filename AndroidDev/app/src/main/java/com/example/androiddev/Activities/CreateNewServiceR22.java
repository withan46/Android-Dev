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

public class CreateNewServiceR22 extends AppCompatActivity {

    private String myIP;
    private EditText name;
    private EditText code;
    private EditText price;
    private EditText description;
    private String clinic_vat_number;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_service_r22);
        Intent intent = getIntent();
        clinic_vat_number = intent.getStringExtra("clinic_vat_number");
        myIP = intent.getStringExtra("ip");
        name = findViewById(R.id.name_tb);
        code = findViewById(R.id.codeName_tb);
        price = findViewById(R.id.price_tb);
        description = findViewById(R.id.description_tb);
    }

    public void createService(View view)  {
        String url= "http://"+myIP+"/flexFitDBServices/createNewServiceR2.php?" + "code=" + code.getText() + "&name=" + name.getText() + "&description=" + description.getText()
                + "&price=" + price.getText() + "&clinic_vat_number=" + clinic_vat_number;
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            boolean isSuccessful = okHttpHandler.createNewService(url);
            if(isSuccessful)
                Toast.makeText(getApplicationContext(), "Service created successfully!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Unable to create the service..", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
