package com.example.androiddev.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddev.HelpfulClasses.CreateAppointmentItemsR6;
import com.example.androiddev.R;

import java.util.ArrayList;

public class recyclerViewAdapterR6 extends RecyclerView.Adapter<recyclerViewAdapterR6.MyViewHolder> {
    private final Context context;
    private final ArrayList<CreateAppointmentItemsR6> appointments;

    public recyclerViewAdapterR6(Context context, ArrayList<CreateAppointmentItemsR6> appointments) {
        this.context = context;
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.appointment_item_r6, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CreateAppointmentItemsR6 appointment = appointments.get(position);
        holder.patientName.setText(appointment.getPatient_name());
        holder.hour.setText(appointment.getTime());
        holder.description.setText(appointment.getDescription());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView patientName, hour, description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patient_name);
            hour = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.description);
        }
    }
}
