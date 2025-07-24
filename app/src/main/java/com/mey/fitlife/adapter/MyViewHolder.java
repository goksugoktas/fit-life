package com.mey.fitlife.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.mey.fitlife.R;

public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imgExercise;
    TextView tvExerciseName, tvSetCount,tvCal;
    MaterialCardView btnApply;

    // egzersiz card tasarımındaki tasarım nesneleri tanımlanır
    public  MyViewHolder(@NonNull View itemView){
        super(itemView);
        imgExercise = itemView.findViewById(R.id.imgExercise);
        tvExerciseName = itemView.findViewById(R.id.tvExerciseName);
        tvSetCount = itemView.findViewById(R.id.tvSetCount);
        tvCal = itemView.findViewById(R.id.tvCal);
        btnApply = itemView.findViewById(R.id.btnApply);




    }


}
