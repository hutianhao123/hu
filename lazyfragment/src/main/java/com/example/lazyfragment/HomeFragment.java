package com.example.lazyfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends LazyFragment {




    @Override
    protected void initView(View view) {

    }

    @Override
    protected int layout() {
        return R.layout.fragment_home;
    }
    @Override
    protected void fragmentLoad() {
        super.fragmentLoad();
        Log.e("---------", "onCreate: HomeFragment" + "开始记载");

    }

    @Override
    protected void fragmentStop() {
        super.fragmentStop();
        Log.e("---------", "onCreate: HomeFragment" + "停止加载");
    }
}