package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateNewService extends AppCompatActivity {

    private final String myIP = "192.168.178.86";
    private EditText name;
    private EditText code;
    private EditText price;
    private EditText description;
    private String clinic_vat_number;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_service);
        name = findViewById(R.id.name_tb);
        code = findViewById(R.id.codeName_tb);
        price = findViewById(R.id.price_tb);
        description = findViewById(R.id.description_tb);
        Intent intent = getIntent();
        clinic_vat_number = intent.getStringExtra("clinic_vat_number");
    }

    public void createService(View view)  {
        String url= "http://"+myIP+"/FlexFit/createNewService.php?" + "code=" + code.getText() + "&name=" + name.getText() + "&description=" + description.getText()
                + "&price=" + price.getText() + "&clinic_vat_number=" + clinic_vat_number;
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            boolean isSuccessful = okHttpHandler.createNewService(url);
            if(isSuccessful)
                Toast.makeText(getApplicationContext(), "Service created successfully!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Unable to create the service..", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
