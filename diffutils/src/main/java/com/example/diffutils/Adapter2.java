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

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.Vh> {


    private List<UserBean> datas;
    private Context context;


    public Adapter2(List<UserBean> datas, Context context) {

        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<UserBean> newData) {
        datas = newData;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new Vh(view);
    }

//    public UserBean getItem(int p) {
//        return asyncListDiffer.getCurrentList().get(p);
//    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        Log.e("---", "onBindViewHolder: " + position);
        holder.title.setText(datas.get(position).getTit());
        holder.msg.setText(datas.get(position).getMsg());

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
        return datas.size();
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
