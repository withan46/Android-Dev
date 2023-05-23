package com.example.androiddev;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarAdapterR6 extends RecyclerView.Adapter<CalendarViewHolderR6>
{
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    public CalendarAdapterR6(ArrayList<LocalDate> days, OnItemListener onItemListener)
    {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolderR6 onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_item_r6, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        else // week view
            layoutParams.height = (int) (parent.getHeight() * 0.5);




        return new CalendarViewHolderR6(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolderR6 holder, int position)
    {
        // Set the day of the month and day of the week in the ViewHolder
        final LocalDate date = days.get(position);
        if(date == null){
            holder.dayOfMonth.setText("");
            holder.dayOfWeek.setText("");}
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                holder.dayOfWeek.setText(date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()));
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return days.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, LocalDate date);
    }
}