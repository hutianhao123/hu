package com.example.car;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class MyAdapter extends RecyclerView.Adapter {

    private static int ViewTypeHead = 0;
    private static int ViewTypeBody = 1;
    private List<Object> list;

    public MyAdapter(List<Object> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewTypeHead) {
            View itemView = View.inflate(parent.getContext(), R.layout.item_header, null);
            return new ItemHeadViewHolder(itemView);
        } else {
            View itemView = View.inflate(parent.getContext(), R.layout.item_body, null);
            return new ItemBodyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (ViewTypeHead == getItemViewType(position)) {
            ShopBean info = (ShopBean) list.get(position);
            ItemHeadViewHolder holder = (ItemHeadViewHolder) viewHolder;
            holder.tv_shop_name.setText(info.getShopName());
            holder.tv_shop_select.setSelected(info.getShopSelect());
            //商铺被选中
            holder.tv_shop_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnShopSelectListener.selectShop(position);
                }
            });
        } else if (ViewTypeBody == getItemViewType(position)) {
            CartlistBean info = (CartlistBean) list.get(position);
            ItemBodyViewHolder holder = (ItemBodyViewHolder) viewHolder;
            holder.tv_product_name.setText(info.getProductName());
            holder.tv_product_select.setSelected(info.getIsSelect());
            holder.tv_product_price.setText(info.getPrice());
            holder.tv_product_color.setText(info.getColor());
            holder.tv_product_size.setText(info.getSize());
            holder.tv_product_count.setText("" + info.getCount());

            //减
            holder.tv_product_subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnCutListener.cutCount(position);
                }
            });
            //加
            holder.tv_product_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnAddListener.addCount(position);
                }
            });
            //删除
            holder.tv_product_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnDeleteListener.DeleteProduct(position);
                }
            });
            //商品被选中
            holder.tv_product_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnProductSelectListener.selectProduct(position);
                }
            });
        }
        if (mOnRefreshListener != null) {
            mOnRefreshListener.refresh(true);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof ShopBean) {
            return ViewTypeHead;
        } else {
            return ViewTypeBody;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHeadViewHolder extends RecyclerView.ViewHolder {
        TextView tv_shop_select;
        TextView tv_shop_name;

        public ItemHeadViewHolder(View itemView) {
            super(itemView);
            tv_shop_select = (TextView) itemView.findViewById(R.id.tv_shop_select);
            tv_shop_name = (TextView) itemView.findViewById(R.id.tv_shop_name);
        }
    }

    public class ItemBodyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_product_name;
        TextView tv_product_select;
        ImageView iv_product;
        TextView tv_product_price;
        TextView tv_product_color;
        TextView tv_product_size;
        TextView tv_product_subtract;
        TextView tv_product_count;
        TextView tv_product_add;
        TextView tv_product_delete;

        public ItemBodyViewHolder(View itemView) {
            super(itemView);
            tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
            tv_product_select = (TextView) itemView.findViewById(R.id.tv_product_select);
            iv_product = (ImageView) itemView.findViewById(R.id.iv_product);
            tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
            tv_product_color = (TextView) itemView.findViewById(R.id.tv_product_color);
            tv_product_size = (TextView) itemView.findViewById(R.id.tv_product_size);
            tv_product_subtract = (TextView) itemView.findViewById(R.id.tv_product_subtract);
            tv_product_count = (TextView) itemView.findViewById(R.id.tv_product_count);
            tv_product_add = (TextView) itemView.findViewById(R.id.tv_product_add);
            tv_product_delete = (TextView) itemView.findViewById(R.id.tv_product_delete);
        }
    }

    //增加数量
    public interface OnAddListener {
        void addCount(int position);
    }

    public OnAddListener mOnAddListener;

    public void setmOnAddListener(OnAddListener mOnAddListener) {
        this.mOnAddListener = mOnAddListener;
    }

    //减少数量
    public interface OnCutListener {
        void cutCount(int position);
    }

    public OnCutListener mOnCutListener;

    public void setmOnCutListener(OnCutListener mOnCutListener) {
        this.mOnCutListener = mOnCutListener;
    }

    //删除商品
    public interface OnDeleteListener {
        void DeleteProduct(int position);
    }

    public OnDeleteListener mOnDeleteListener;

    public void setmOnDeleteListener(OnDeleteListener mOnDeleteListener) {
        this.mOnDeleteListener = mOnDeleteListener;
    }

    //商品被选中
    public interface OnProductSelectListener {
        void selectProduct(int position);
    }

    public OnProductSelectListener mOnProductSelectListener;

    public void setOnProductSelectListener(OnProductSelectListener mOnPorductSelectListener) {
        this.mOnProductSelectListener = mOnPorductSelectListener;
    }

    //商铺被选中
    public interface OnShopSelectListener {
        void selectShop(int position);
    }

    public OnShopSelectListener mOnShopSelectListener;

    public void setOnShopSelectListener(OnShopSelectListener mOnShopSelectListener) {
        this.mOnShopSelectListener = mOnShopSelectListener;
    }

    //监控购物车列表选中情况
    public interface OnRefreshListener {
        void refresh(boolean havaSelect);//是否有选中item
    }

    public OnRefreshListener mOnRefreshListener;

    public void setOnRefreshListener(OnRefreshListener mOnRefreshListener) {
        this.mOnRefreshListener = mOnRefreshListener;
    }

}
