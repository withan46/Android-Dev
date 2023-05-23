package com.example.androiddev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EconomicMovementsR10Adapter extends RecyclerView.Adapter<EconomicMovementsR10Adapter.ViewHolder>  {
    private ArrayList<Movement> movements = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movements_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

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

    public void setMovements(ArrayList<Movement> movements) {
        this.movements = movements;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dateView, descriptionView, costView;
        private LinearLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.date);
            descriptionView = itemView.findViewById(R.id.description);
            costView = itemView.findViewById(R.id.cost);
            parent = itemView.findViewById(R.id.parent);
        }
    }

}
