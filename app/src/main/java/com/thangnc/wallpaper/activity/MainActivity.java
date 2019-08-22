package com.thangnc.wallpaper.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thangnc.wallpaper.R;
import com.thangnc.wallpaper.fragment.FavouriteFragment;
import com.thangnc.wallpaper.fragment.LatestFragment;
import com.thangnc.wallpaper.fragment.PhotoFragment;
import com.thangnc.wallpaper.utils.Functions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_category:
                    mTextMessage.setText(R.string.title_category);
                    LatestFragment latestFragment = new LatestFragment();
                    Functions.changeFragment(MainActivity.this, latestFragment);
                    return true;
                case R.id.navigation_photo:
                    mTextMessage.setText(R.string.title_photo);
                    PhotoFragment photoFragment = new PhotoFragment();
                    Functions.changeFragment(MainActivity.this, photoFragment);
                    return true;
                case R.id.navigation_favourite:
                    mTextMessage.setText(R.string.title_favourite);
                    FavouriteFragment favouriteFragment = new FavouriteFragment();
                    Functions.changeFragment(MainActivity.this, favouriteFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        LatestFragment latestFragment = new LatestFragment();
        Functions.changeFragment(MainActivity.this, latestFragment);
        mTextMessage.setText(R.string.title_category);
    }

}
