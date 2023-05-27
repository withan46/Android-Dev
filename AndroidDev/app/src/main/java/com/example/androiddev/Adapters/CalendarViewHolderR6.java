package com.example.androiddev.Adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androiddev.R;

public class CalendarViewHolderR6 extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView dayTextView;
    public final TextView dayOfMonth;
    public final TextView dayOfWeek;

    @SuppressLint("CutPasteId")
    public CalendarViewHolderR6(@NonNull View itemView)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
        itemView.setOnClickListener(this);
        dayTextView = itemView.findViewById(R.id.cellDayText);
    }


    @Override
    public void onClick(View view) {

    }
}