package com.example.lazyfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyFragment extends Fragment {
    private View view;
    boolean isViewCreated = false;//根布局是否创建
    boolean currentVisibleState = false;//view是否可见


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layout(), container, false);
        initView(view);
        isViewCreated = true;
        if (getUserVisibleHint()) {//获取用户是否可见--因为setUserVisibleHint在oncreate前调用所以已经分发过，所以创建视图后在分发一次
            diapthUserVisibleHint(true);//分发
        }
        return view;
    }

    protected abstract void initView(View view);

    protected abstract int layout();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {//设置用户是否可见
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated) {//布局已经创建
            if (isVisibleToUser && !currentVisibleState) {
                diapthUserVisibleHint(true);
            } else if (!isVisibleToUser && currentVisibleState) {
                diapthUserVisibleHint(false);
            }
        }

    }

    //分发fragment可见事件
    protected void diapthUserVisibleHint(boolean isVisible) {
        if (currentVisibleState==isVisible){//
            return;
        }
        currentVisibleState = isVisible;
        if (isVisible) {
            fragmentLoad();
        } else {
            fragmentStop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (currentVisibleState&&getUserVisibleHint()){
            diapthUserVisibleHint(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!currentVisibleState&&getUserVisibleHint()){
            diapthUserVisibleHint(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated=false;
        currentVisibleState=false;
    }

    protected void fragmentStop() {

    }

    protected void fragmentLoad() {

    }


}
