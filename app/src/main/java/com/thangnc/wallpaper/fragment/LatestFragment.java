package com.thangnc.wallpaper.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.thangnc.wallpaper.R;
import com.thangnc.wallpaper.adapter.LatestAdapter;
import com.thangnc.wallpaper.api.ApiUtil;
import com.thangnc.wallpaper.models.latest.Latest;
import com.thangnc.wallpaper.utils.EndlessRecyclerViewScrollListener;
import com.thangnc.wallpaper.utils.Functions;
import com.thangnc.wallpaper.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestFragment extends Fragment {

    SwipeRefreshLayout swipe;
    List<Latest> latests;
    LatestAdapter latestAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rvLatest;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        latests = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getContext());
        final FragmentManager fragmentManager = getFragmentManager();

        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        swipe = view.findViewById(R.id.swipe);
        latestAdapter = new LatestAdapter(getContext(), latests, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Latest latest = latests.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id",latest.getId().toString());
                PhotoFragment photoFragment = new PhotoFragment();
                photoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame_layout, photoFragment);
                fragmentTransaction.commit();
            }
        });
        rvLatest.setAdapter(latestAdapter);
        rvLatest.setLayoutManager(linearLayoutManager);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                latests.clear();
                getLatest();
            }
        });
        rvLatest.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getLatest();
            }
        });
        return view;
    }

    private void getLatest() {
        ApiUtil.getInstance().getLastest().enqueue(new Callback<List<Latest>>() {
            @Override
            public void onResponse(Call<List<Latest>> call, Response<List<Latest>> response) {
                swipe.setRefreshing(false);
                if (response.body().size() == 0){
                    rvLatest.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                        @Override
                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                        }
                    });
                    latestAdapter.setOnLoadMore(false);
                }
                latests.addAll(response.body());
                latestAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Latest>> call, Throwable t) {
                swipe.setRefreshing(false);
                Log.d("loaderror", t.getMessage());
            }
        });
    }
}
