package com.example.lazyfragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends LazyFragment {


    @Override
    protected void initView(View view) {

    }

    @Override
    protected int layout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("---------", "onCreate: SearchFragment" +"创建");
    }

    @Override
    protected void fragmentLoad() {
        super.fragmentLoad();
        Log.e("---------", "onCreate: SearchFragment" + "开始记载");

    }

    @Override
    protected void fragmentStop() {
        super.fragmentStop();
        Log.e("---------", "onCreate: SearchFragment" + "停止加载");
    }
}