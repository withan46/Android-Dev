package com.example.androiddev.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddev.MainClasses.EconomicMovements;
import com.example.androiddev.R;

import java.util.ArrayList;

public class EconomicMovementsR10Adapter extends RecyclerView.Adapter<EconomicMovementsR10Adapter.ViewHolder>  {
    private ArrayList<EconomicMovements> movements = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movements_list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dateView.setText((CharSequence) movements.get(position).getDate());
        holder.descriptionView.setText(movements.get(position).getDescription());
        holder.costView.setText(Double.toString(movements.get(position).getCost()));
    }

    @Override
    public int getItemCount() {
        return movements.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovements(ArrayList<EconomicMovements> movements) {
        this.movements = movements;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateView;
        private final TextView descriptionView;
        private final TextView costView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.date);
            descriptionView = itemView.findViewById(R.id.description);
            costView = itemView.findViewById(R.id.cost);
        }
    }

}
