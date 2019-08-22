package com.thangnc.wallpaper.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thangnc.wallpaper.R;

public class LatestHolder extends RecyclerView.ViewHolder {

    public ImageView ivLatest;
    public TextView tvLatest;
    public LatestHolder(@NonNull View itemView) {
        super(itemView);
        ivLatest = itemView.findViewById(R.id.ivLatest);
        tvLatest = itemView.findViewById(R.id.tvLatest);
    }
}
