package com.example.diffutils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Vh> {

    private AsyncListDiffer<UserBean> asyncListDiffer;
    private Context context;


    public Adapter(Context context) {
        this.context = context;
        asyncListDiffer = new AsyncListDiffer<UserBean>(this, new MyItemCallBack());
    }
//****
    public void setDatas(List<UserBean> newData) {
        asyncListDiffer.submitList(newData);
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new Vh(view);
    }

    public UserBean getItem(int p) {
        return asyncListDiffer.getCurrentList().get(p);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        Log.e("---", "onBindViewHolder: " + position);
        holder.title.setText(getItem(position).getTit());
        holder.msg.setText(getItem(position).getMsg());

    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            String tit = bundle.getString("tit");
            String msg = bundle.getString("msg");
            if (!TextUtils.isEmpty(tit)) {
                holder.title.setText(tit);
            }
            if (!TextUtils.isEmpty(msg)) {
                holder.msg.setText(msg);
            }
        }

    }

    @Override
    public int getItemCount() {
        return asyncListDiffer.getCurrentList().size();
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView title;
        TextView msg;

        public Vh(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            msg = itemView.findViewById(R.id.msg);
        }
    }

}
