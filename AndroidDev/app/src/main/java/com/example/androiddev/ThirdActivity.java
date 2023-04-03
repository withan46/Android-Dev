package com.example.androiddev;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.xmlpull.v1.XmlPullParser;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toast.makeText(this, "Still in progress", Toast.LENGTH_SHORT).show();

    }

    public void register(View view) {
        Toast.makeText(this, "Nothing works stop trying", Toast.LENGTH_SHORT).show();

    }


    public void loginLink(View view) {
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
