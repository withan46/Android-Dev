package com.example.androiddev;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class r6_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r6_weekly_planner);

        TextView monthTextView = findViewById(R.id.text_month);
        ImageButton prevMonthButton = findViewById(R.id.button_previous);
        ImageButton nextMonthButton = findViewById(R.id.button_next);
        Calendar calendar = Calendar.getInstance();

        int currentMonth = calendar.get(Calendar.MONTH);
        String monthName = new DateFormatSymbols().getMonths()[currentMonth];
        monthTextView.setText(monthName);

        prevMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                int previousMonth = calendar.get(Calendar.MONTH);
                String previousMonthName = new DateFormatSymbols().getMonths()[previousMonth];
                monthTextView.setText(previousMonthName);
            }
        });

        nextMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                int nextMonth = calendar.get(Calendar.MONTH);
                String nextMonthName = new DateFormatSymbols().getMonths()[nextMonth];
                monthTextView.setText(nextMonthName);
            }
        });
    }
}
