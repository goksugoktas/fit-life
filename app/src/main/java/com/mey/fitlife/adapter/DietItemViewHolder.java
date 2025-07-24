package com.mey.fitlife.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.mey.fitlife.R;

public class DietItemViewHolder extends RecyclerView.ViewHolder {
    TextView tvDietName, tvCal, tvBreakfastPlan, tvLaunchPlan, tvDinnerPlan;
    MaterialCardView btnApply;

    // diyet card tasarımındaki tasarım nesneleri tanımlanır
    public DietItemViewHolder(@NonNull View itemView) {
        super(itemView);
        tvDietName = itemView.findViewById(R.id.tvDietName);
        tvCal = itemView.findViewById(R.id.tvCal);
        tvBreakfastPlan = itemView.findViewById(R.id.tvBreakfastPlan);
        tvLaunchPlan = itemView.findViewById(R.id.tvLaunchPlan);
        tvDinnerPlan = itemView.findViewById(R.id.tvDinnerPlan);
        btnApply = itemView.findViewById(R.id.btnApply);


    }

}
