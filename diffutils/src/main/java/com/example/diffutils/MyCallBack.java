package com.example.diffutils;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MyCallBack extends DiffUtil.Callback {
    private List<UserBean> oldList;
    private List<UserBean> newList;
    //开始


    public MyCallBack(List<UserBean> oldList, List<UserBean> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override//判断id是否相同 不同返回false继续下一个数据
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        UserBean oldbean = oldList.get(oldItemPosition);
        UserBean newbean = newList.get(newItemPosition);
        return oldbean.getId() == newbean.getId();
    }

    @Override//当areItemsTheSame为true才会执行
    //比较数据是否相同  相同返回true 然后继续下一个数据
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        UserBean oldbean = oldList.get(oldItemPosition);
        UserBean newbean = newList.get(newItemPosition);
        if (!TextUtils.equals(oldbean.getTit(),newbean.getTit())){
            return  false;
        }
        if (!TextUtils.equals(oldbean.getMsg(),newbean.getMsg())){
            return  false;
        }
        return true;
    }

    @Nullable
    @Override//当areContentsTheSame返回false才会执行
    //数据不同更新view
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        UserBean oldbean = oldList.get(oldItemPosition);
        UserBean newbean = newList.get(newItemPosition);
        Bundle bundle = new Bundle();
        if (!TextUtils.equals(oldbean.getTit(),newbean.getTit())){
            bundle.putString("tit",newbean.getTit());

        }
        if (!TextUtils.equals(oldbean.getMsg(),newbean.getMsg())){
            bundle.putString("msg",newbean.getMsg());

        }


        return bundle;
    }
}
