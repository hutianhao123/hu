package com.example.lazyfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.BaseIndicator;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private List<Fragment> datas = new ArrayList<>();

    private HomeFragment homeFragment = new HomeFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private MyFragment myFragment = new MyFragment();
    private ImageView image;
    private ImageView image2;
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
//        image = (ImageView) findViewById(R.id.image);
//        image2 = (ImageView) findViewById(R.id.image2);
//        RequestOptions options = new RequestOptions();
//        RoundedCorners roundedCorners = new RoundedCorners(20);
//        options.transform(roundedCorners);
//        Glide.with(this).load("https://i2.hdslb.com/bfs/face/7c15331da42d8bdd742bfe1364a805f7034494c1.jpg@52w_52h.webp")
//                .format(DecodeFormat.PREFER_RGB_565).into(image2);
//        Glide.with(this).load("https://i2.hdslb.com/bfs/face/7c15331da42d8bdd742bfe1364a805f7034494c1.jpg@52w_52h.webp")
//                .format(DecodeFormat.PREFER_ARGB_8888).into(image);
        String s = MD5Utils.toMD5("123");
        Log.e("----", "initView: " + s);
//        Glide.with(this).load(R.drawable.ic_launcher_background)
//                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3))).into(image);
        datas.add(homeFragment);
        datas.add(searchFragment);
        datas.add(myFragment);
        vp.setAdapter(new Adapter(getSupportFragmentManager()));
        vp.setOffscreenPageLimit(1);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2);
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rb1.setChecked(true);
                } else if (position == 1) {
                    rb2.setChecked(true);
                } else if (position == 2) {
                    rb3.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        List<DataBean> dats = new ArrayList<>();
        final List<DataBean> titls = new ArrayList<>();

        titls.add(new DataBean("https://i2.hdslb.com/bfs/face/7c15331da42d8bdd742bfe1364a805f7034494c1.jpg@52w_52h.webp"));
        titls.add(new DataBean("https://i2.hdslb.com/bfs/face/716dc4d60a71266fe4eda42d14a0451530d5960d.jpg@52w_52h.webp"));
        titls.add(new DataBean("https://i1.hdslb.com/bfs/face/9c525c1a1196dd2f5bcb60598843d7a2181a9c8f.jpg@52w_52h.webp"));
        dats.add(new DataBean(R.drawable.ic_launcher_background));
        dats.add(new DataBean(R.drawable.ic_launcher_foreground));
        dats.add(new DataBean(R.drawable.ic_launcher_background));
        dats.add(new DataBean(R.drawable.ic_launcher_background));
        dats.add(new DataBean(R.drawable.ic_launcher_foreground));
        banner = (Banner) findViewById(R.id.banner);
        banner.setIndicator(new BaseIndicator(this));
//        banner.setIndicator(new RectangleIndicator(this));//---
        banner.setIndicator(new RoundLinesIndicator(this));//——

        //banner.setIndicator(new CircleIndicator(this));//指示器（小圆点）
//        banner.setAdapter(new BAdapter(dats));//自己写的适配器
        //banner.setIndicatorWidth(20,20);
        //   banner.setIndicatorHeight(5);
//        banner.setBannerGalleryMZ(50, 0.5f);
        banner.setBannerGalleryEffect(10,10,0.5f);
        banner.setAdapter(new BannerImageAdapter<DataBean>(titls) {//只加载图片
            @Override
            public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
                //图片加载自己实现
                Glide.with(holder.itemView)
                        .load(data.getImg())
                        .transform(new RoundedCorners(50))
//                        .transform(new CircleCrop())
                        //.apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        });
        banner.start();

//        banner.setAdapter(new BannerImageAdapter<DataBean>() {
//            @Override
//            public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
//                Glide.with(MainActivity.this).load(data.getImage()).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
//            }
//        });
//
//        //banner.setImages(dats);
//        banner.start();
    }

    class BAdapter extends BannerAdapter<DataBean, BAdapter.ImageHolder> {
        public BAdapter(List<DataBean> datas) {
            super(datas);
        }

        @Override
        public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
            return new ImageHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.banner_item, parent, false));
        }

        @Override
        public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
            holder.imageView.setImageResource(data.getImage());
        }

        public class ImageHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ImageHolder(@NonNull View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image_view_item);
            }

        }
    }

    class Adapter extends FragmentStatePagerAdapter {

        public Adapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
