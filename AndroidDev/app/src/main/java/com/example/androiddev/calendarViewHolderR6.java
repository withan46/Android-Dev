package com.example.androiddev;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class calendarViewHolderR6 extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView dayTextView;
    private final ArrayList<LocalDate> days;
    public final TextView dayOfMonth;
    public final TextView dayOfWeek;
    private final calendarAdapterR6.OnItemListener onItemListener;
    public calendarViewHolderR6(@NonNull View itemView, calendarAdapterR6.OnItemListener onItemListener, ArrayList<LocalDate> days)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        dayTextView = itemView.findViewById(R.id.cellDayText);
        this.days = days;
    }


    @Override
    public void onClick(View view) {

    }
}