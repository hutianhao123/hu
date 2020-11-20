package com.example.lazyfragment;


import android.util.Log;
import android.view.View;

public class MyFragment extends LazyFragment {


    @Override
    protected void initView(View view) {

    }

    @Override
    protected int layout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void fragmentLoad() {
        super.fragmentLoad();
        Log.e("---------", "onCreate: MyFragment" + "开始记载");

    }

    @Override
    protected void fragmentStop() {
        super.fragmentStop();
        Log.e("---------", "onCreate: MyFragment" + "停止加载");
    }
}