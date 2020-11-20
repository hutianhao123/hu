package com.example.diffutils;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swip;
    private RecyclerView rv;
    private List<UserBean> datas = new ArrayList<>();
    private Adapter2 adapter;
   private String[] jobArray = {"1", "2", "3"};
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        swip = (SwipeRefreshLayout) findViewById(R.id.swip);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        for (int i = 0; i < 8; i++) {
            datas.add(new UserBean(i, "user" + String.valueOf(i), jobArray[0]));
        }
        adapter = new Adapter2(datas,this);
        //adapter.setDatas(datas);
        rv.setAdapter(adapter);
        swip.setOnRefreshListener(this);
        swip.setColorSchemeResources(R.color.colorAccent);

    }
//    private int i = 10;
    @Override
    public void onRefresh() {
        List<UserBean> newdatas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String desc = null;
            if (i % 3 == 0) {
                desc = jobArray[random.nextInt(jobArray.length)];
            } else {
                desc = jobArray[0];
            }
            newdatas.add(new UserBean(i, "user" + i, desc));
        }
        if (datas.size() % 2 == 0) {
            newdatas.add(2, new UserBean(100, "user" + 100, jobArray[0]));
        }
//        datas.add(2, new UserBean(100, "user" + (++i), jobArray[0]));
        //Log.e("---", "onRefresh: " + i);
        DiffUtil.DiffResult diff = DiffUtil.calculateDiff(new MyCallBack(datas, newdatas));

        datas = newdatas;
        adapter.setDatas(datas);
         diff.dispatchUpdatesTo(adapter);

        swip.setRefreshing(false);
    }


}