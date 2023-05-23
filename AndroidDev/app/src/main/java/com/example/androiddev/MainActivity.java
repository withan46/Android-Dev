package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final String myIP = "192.168.2.4";
    private UserListR1 usl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }


    public void registerLink(View view){
        Intent intent = new Intent(MainActivity.this, mainActivityR6.class);
        intent.putExtra("ip", myIP);
        startActivity(intent);
    }


    public void signInOnClick(View view){
        TextView emailText = findViewById(R.id.email);
        TextView passwordText = findViewById(R.id.password);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        UserListR1 userList = new UserListR1(myIP);
        List<String> all_emails = userList.getAllEmails();
        List<String> all_passwords = userList.getAllPassword();
        List<String> all_names = userList.getAllNames();

        boolean flag = true;
        for (int i = 0; i < all_emails.size(); i++){
            if(Objects.equals(all_emails.get(i), email)){
                if(Objects.equals(all_passwords.get(i), password)){
                    Log.i("Tag:", all_emails.get(i));
                    if(all_emails.get(i).contains("@doctor.com")){
                        Intent intent = new Intent(MainActivity.this, DoctorActivityR1.class);
                        intent.putExtra("message", all_names.get(i));
                        startActivity(intent);
                    }else if (all_emails.get(i).contains("@patient.com")){
                        Intent intent = new Intent(MainActivity.this, PatientActivityR1.class);
                        intent.putExtra("message", all_names.get(i));
                        startActivity(intent);
                    }else if (all_emails.get(i).contains("@psf.com")){
                        Intent intent = new Intent(MainActivity.this, PsfActivityR1.class);
                        intent.putExtra("message", all_names.get(i));
                        startActivity(intent);
                    }
                    flag = false;
                }
            }
        }
        if (flag){
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
        }

    }


}