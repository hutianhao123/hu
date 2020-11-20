package com.example.car;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvCheckAll;
    private TextView tvTotalPrice;
    private TextView tvTotalCount;
    private TextView tvSettlement;

    private Context context = this;
    private MyAdapter adapter;
    private ArrayList<Object> list = new ArrayList<>();

    private Boolean isCheckAll = false;
    int num = 0;
    float totalPrice = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        initData();

        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
        //减少商品
        lowshop();
        //添加商品
        addshop();
        //删除商品
        delshop();
        //全选
        checkall();
        //选中商品
        selectShop();
        //选中商铺
        selectGood();
        //监控全局
        staticAdapter();

    }

    //监控全局
    private void staticAdapter() {
        adapter.setOnRefreshListener(new MyAdapter.OnRefreshListener() {
            @Override
            public void refresh(boolean havaSelect) {
                int totalCount = 0;
                float totalPrice = 0.0f;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof CartlistBean) {
                        CartlistBean info = (CartlistBean) list.get(i);
                        if (info.getIsSelect()) {
                            totalCount = totalCount + info.getCount();
                            totalPrice = totalPrice + info.getCount() * Float.parseFloat(info.getPrice());
                        }
                    }
                }
                tvTotalCount.setText("共" + totalCount + "件商品");
                tvTotalPrice.setText("总价：" + totalPrice);

                //全部选中，那么底部勾选“全选”
                boolean isCheckAll = false;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof CartlistBean) {
                        CartlistBean info = (CartlistBean) list.get(i);
                        if (!info.getIsSelect()) {
                            isCheckAll = false;
                            break;
                        } else {
                            isCheckAll = true;
                        }
                    }
                }
                tvCheckAll.setSelected(isCheckAll);
            }
        });
    }

    //选中商铺
    private void selectGood() {
        adapter.setOnShopSelectListener(new MyAdapter.OnShopSelectListener() {
            @Override
            public void selectShop(int position) {
                //选中商铺
                ShopBean info = (ShopBean) list.get(position);
                info.setShopSelect(!info.getShopSelect());
                //选中商品
                for (int i = position + 1; i < list.size(); i++) {
                    if (list.get(i) instanceof ShopBean) {
                        //是商铺，则结束循环
                        break;
                    } else {
                        //非商铺，选中[position + 1,i)之间的商品
                        CartlistBean info2 = (CartlistBean) list.get(i);
                        info2.setSelect(info.getShopSelect());
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    //选中商品
    private void selectShop() {
        adapter.setOnProductSelectListener(new MyAdapter.OnProductSelectListener() {
            @Override
            public void selectProduct(int position) {
                CartlistBean info = (CartlistBean) list.get(position);
                info.setSelect(!info.getIsSelect());
                int m = 0;//商铺的position--该商品所在的商铺
                int n = 0;//下一间商铺的position
                //取值m
                for (int i = position - 1; i > 0; i--) {
                    if (list.get(i) instanceof ShopBean) {
                        m = i;
                        break;
                    }
                }
                //取值n
                for (int i = position + 1; i < list.size(); i++) {
                    if (list.get(i) instanceof ShopBean) {
                        n = i;
                        break;
                    } else {
                        n = list.size();
                    }
                }
                //将该商铺下的商品是否选中的而状态放入集合selectList
                ArrayList<Boolean> selectList = new ArrayList<>();
                for (int i = m + 1; i < n; i++) {
                    CartlistBean info2 = (CartlistBean) list.get(i);
                    selectList.add(info2.getIsSelect());
                }
                //如果全是true，那么商铺页选true
                ShopBean shopInfo = (ShopBean) list.get(m);
                if (selectList.contains(false)) {
                    shopInfo.setShopSelect(false);
                } else {
                    shopInfo.setShopSelect(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    //全选
    private void checkall() {
        tvCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = 0;
                totalPrice = 0.0f;
                isCheckAll = !isCheckAll;
                if (isCheckAll) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof ShopBean) {
                            ShopBean info = (ShopBean) list.get(i);
                            info.setShopSelect(isCheckAll);
                        } else {
                            CartlistBean info = (CartlistBean) list.get(i);
                            info.setSelect(isCheckAll);
                            num = num + 1;
                            totalPrice = totalPrice + Float.parseFloat(info.getPrice()) * Float.parseFloat(info.getPrice());
                        }
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) instanceof ShopBean) {
                            ShopBean info = (ShopBean) list.get(i);
                            info.setShopSelect(isCheckAll);
                        } else {
                            CartlistBean info = (CartlistBean) list.get(i);
                            info.setSelect(isCheckAll);
                        }
                    }
                    num = 0;
                    totalPrice = 0.0f;
                }
                tvCheckAll.setSelected(isCheckAll);
                adapter.notifyDataSetChanged();
                tvTotalCount.setText("共" + num + "件商品");
                tvTotalPrice.setText("总价：" + totalPrice);
            }
        });
    }

    //删除商品
    private void delshop() {
        adapter.setmOnDeleteListener(new MyAdapter.OnDeleteListener() {
            @Override
            public void DeleteProduct(int position) {
                list.remove(position);
                if (list.get(position - 1) instanceof ShopBean) {
                    if (position - 1 == (list.size() - 1) || list.get(position) instanceof ShopBean) {
                        list.remove(position - 1);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    //添加商品
    private void addshop() {
        adapter.setmOnAddListener(new MyAdapter.OnAddListener() {
            @Override
            public void addCount(int position) {
                CartlistBean info = (CartlistBean) list.get(position);
                info.setCount(info.getCount() + 1);
                adapter.notifyDataSetChanged();
            }
        });
    }

    //减少商品
    private void lowshop() {
        adapter.setmOnCutListener(new MyAdapter.OnCutListener() {
            @Override
            public void cutCount(int position) {
                CartlistBean info = (CartlistBean) list.get(position);
                if (info.getCount() > 1) {
                    info.setCount(info.getCount() - 1);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "商品数量必须大于等于1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //加载数据
    private void initData() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                if (i % 2 == 0) {
                    ShopBean shopBean = new ShopBean();
                    shopBean.setShopId(1);
                    shopBean.setShopName("李宁旗舰店");
                    shopBean.setShopSelect(false);
                    list.add(shopBean);
                } else {
                    ShopBean shopBean = new ShopBean();
                    shopBean.setShopId(1);
                    shopBean.setShopName("特步旗舰店");
                    shopBean.setShopSelect(false);
                    list.add(shopBean);
                }
            } else {
                if (i % 2 == 0) {
                    CartlistBean info = new CartlistBean();
                    info.setPrice("1.0");
                    info.setColor("颜色：绿色");
                    info.setProductName("冲锋衣");
                    info.setSize("尺寸：M");
                    info.setCount(1);
                    info.setSelect(false);
                    list.add(info);
                } else {
                    CartlistBean info = new CartlistBean();
                    info.setPrice("2.0");
                    info.setColor("颜色：红色");
                    info.setProductName("衬衫");
                    info.setSize("尺寸：L");
                    info.setCount(2);
                    info.setSelect(false);
                    list.add(info);
                }
            }
        }
    }
    //初始化布局
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tvCheckAll = (TextView) findViewById(R.id.tv_check_all);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        tvTotalCount = (TextView) findViewById(R.id.tv_total_count);
        tvSettlement = (TextView) findViewById(R.id.tv_settlement);
    }
}