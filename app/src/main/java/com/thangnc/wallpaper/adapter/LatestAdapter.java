package com.thangnc.wallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thangnc.wallpaper.R;
import com.thangnc.wallpaper.holder.LatestHolder;
import com.thangnc.wallpaper.holder.LoadHolder;
import com.thangnc.wallpaper.models.latest.Latest;
import com.thangnc.wallpaper.utils.OnItemClickListener;

import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean onLoadMore = true;

    public boolean isOnLoadMore(){
        return onLoadMore;
    }

    public void setOnLoadMore(boolean onLoadMore) {
        this.onLoadMore = onLoadMore;
    }


    Context context;
    List<Latest> latests;
    OnItemClickListener onItemClickListener;

    public LatestAdapter(Context context, List<Latest> latests, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.latests = latests;
        this.onItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.item_latest, parent, false);
            return new LatestHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.loadmore, parent, false);
            return new LoadHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LatestHolder){
            Latest latest = latests.get(position);
            ((LatestHolder) holder).tvLatest.setText(latest.getTitle().getRendered());
            Glide
                    .with(context)
                    .load(latest.getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl())
                    .into(((LatestHolder) holder).ivLatest);
        } else if (holder instanceof LoadHolder){

        }
    }

    int ITEM = 1;
    int LOAD_MORE = 2;
    @Override
    public int getItemViewType(int position) {
        if (onLoadMore){
            if (position == latests.size() -1 )
                return LOAD_MORE;
            else return ITEM;
        }else return ITEM;

    }

    @Override
    public int getItemCount() {
        return latests.size();
    }
}
