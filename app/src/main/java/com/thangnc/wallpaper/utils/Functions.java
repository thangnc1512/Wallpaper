package com.thangnc.wallpaper.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.thangnc.wallpaper.R;

public class Functions {
    public static void changeFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }
}
