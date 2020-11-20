package com.example.diffutils;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MyItemCallBack extends DiffUtil.ItemCallback<UserBean> {


    @Override
    public boolean areItemsTheSame(@NonNull UserBean oldbean, @NonNull UserBean newbean) {

        return oldbean.getId() == newbean.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull UserBean oldbean, @NonNull UserBean newbean) {

        if (!TextUtils.equals(oldbean.getTit(), newbean.getTit())) {
            return false;
        }
        if (!TextUtils.equals(oldbean.getMsg(), newbean.getMsg())) {
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull UserBean oldbean, @NonNull UserBean newbean) {

        Bundle bundle = new Bundle();
        if (!TextUtils.equals(oldbean.getTit(), newbean.getTit())) {
            bundle.putString("tit", newbean.getTit());

        }
        if (!TextUtils.equals(oldbean.getMsg(), newbean.getMsg())) {
            bundle.putString("msg", newbean.getMsg());

        }


        return bundle;
    }


}
