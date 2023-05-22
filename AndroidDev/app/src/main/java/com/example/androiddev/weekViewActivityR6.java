package com.example.androiddev;

import static com.example.androiddev.calendarUtilsR6.daysInWeekArray;
import static com.example.androiddev.calendarUtilsR6.monthYearFromDate;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;


public class weekViewActivityR6 extends AppCompatActivity implements calendarAdapterR6.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initWidgets();
        setWeekView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(calendarUtilsR6.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(calendarUtilsR6.selectedDate);

        calendarAdapterR6 calendarAdapterR6 = new calendarAdapterR6(days, this::onItemClick);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position < 4) {
                    return 1; // First row occupies 1 column per item
                } else {
                    return 2; // Second row occupies 2 columns per item (centered)
                }
            }
        });

        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapterR6);
    }


    public void previousWeekAction(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calendarUtilsR6.selectedDate = calendarUtilsR6.selectedDate.minusWeeks(1);
        }
        setWeekView();
    }

    public void nextWeekAction(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calendarUtilsR6.selectedDate = calendarUtilsR6.selectedDate.plusWeeks(1);
        }
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        calendarUtilsR6.selectedDate = date;
        setWeekView();
    }






}