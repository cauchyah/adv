package com.example.administrator.hei;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class AdActivity extends AppCompatActivity {


    ViewPagerFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);
        fragment= (ViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        List<AdvInfo> list=new ArrayList<>();
        list.add(new AdvInfo(R.drawable.image1));
        list.add(new AdvInfo(R.drawable.image5));
        list.add(new AdvInfo(R.drawable.image2));
        list.add(new AdvInfo(R.drawable.image3));
        list.add(new AdvInfo(R.drawable.image4));
        list.add(new AdvInfo(R.drawable.image1));
        list.add(new AdvInfo(R.drawable.image5));
       /* list.add(new AdvInfo("http://imgsrc.baidu.com/forum/pic/item/f643dc54564e925850c8af029482d158cdbf4e3a.jpg"));
        list.add(new AdvInfo("http://ukrainianphotographers.com/wp-content/uploads/2013/04/stars_001.jpg"));
        list.add(new AdvInfo("http://img4.cache.netease.com/photo/0026/2015-05-19/APVC513454A40026.jpg"));
        list.add(new AdvInfo("http://i2.hdslb.com/video/93/932f76fd9e7856acb8b07d28d438ee3c.jpg"));
        list.add(new AdvInfo("http://dl.bizhi.sogou.com/images/2015/06/26/1214911.jpg"));*/
        fragment.setData(list);
        fragment.setWheel(true);
        fragment.setIndicator(ViewPagerFragment.LEFT,
                ContextCompat.getColor(this,R.color.checkedColor),
                ContextCompat.getColor(this,R.color.uncheckColor)
                );
        fragment.setOnImageClickListener(new ViewPagerFragment.onImageClickListener() {
            @Override
            public void onClick(View view, int position, AdvInfo item) {
                Toast.makeText(AdActivity.this,position+"position",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
