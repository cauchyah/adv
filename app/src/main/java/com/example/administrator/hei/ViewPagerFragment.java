package com.example.administrator.hei;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {

    private static final int WHEEL = 10;
    private static final int WHEEL_WAIT =11 ;
    private ViewPager viewPager;
    private LinearLayout indicatorLayout;
    private List<AdvInfo> list =new ArrayList<AdvInfo>();
    private ViewPagerAdapter adapter;
    private boolean isWheel=true;
    private static final int delayTime=5000;
    private long lastTouchTime;
    public   static final int RIGHT=1;
    public   static final int LEFT=2;
    public   static final int CENTER=3;
    private int checkedColor;
    private int uncheckColor;

    public ViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_view_pager, container, false);
        viewPager= (ViewPager) view.findViewById(R.id.viewPager);
        indicatorLayout= (LinearLayout) view.findViewById(R.id.indicatorLayout);
        return view;
    }
    public void setIndicator(int position,int checkedColor,int uncheckColor){
        this.checkedColor=checkedColor;
        this.uncheckColor=uncheckColor;
        switch (position){
            case LEFT:
                indicatorLayout.setGravity(Gravity.LEFT);
                break;
            case RIGHT:
                indicatorLayout.setGravity(Gravity.RIGHT);
                break;
            case CENTER:
                indicatorLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            default:
                indicatorLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        }
        drawIndicator();

    }

    private void drawIndicator() {
        View dot ;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
        params.leftMargin = 8;
        for (int i = 0; i <list.size(); i++) {
            dot = new View(getContext());
            if(i==0)
            dot.setEnabled(true);
            else
            dot.setEnabled(false);
            dot.setBackgroundResource(R.drawable.dot_selector);
            dot.setLayoutParams(params);
            indicatorLayout.addView(dot);
        }


    }

    public void setData(List<AdvInfo> resIds){
        this.list.clear();
      this.list.addAll(resIds);
        if (adapter==null){
            adapter=new ViewPagerAdapter();
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastTouchTime=System.currentTimeMillis();
                for (int i = 0; i < list.size(); i++) {
                    if (i==position){
                        indicatorLayout.getChildAt(i).setEnabled(true);
                    }
                    else{
                        indicatorLayout.getChildAt(i).setEnabled(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private static class MyHandler extends Handler{


        @Override
        public void handleMessage(Message msg) {
            if(weakReference.get()!=null) {
                ViewPagerFragment fraggment=weakReference.get();
                if (fraggment.list.size() > 0) {
                    if (msg.what == WHEEL) {

                        int position = fraggment.viewPager.getCurrentItem();
                        fraggment.viewPager.setCurrentItem((1 + position) % fraggment.list.size());
                        removeCallbacks(fraggment.task);
                        postDelayed(fraggment.task, delayTime);
                    } else if (msg.what==WHEEL_WAIT){
                        removeCallbacks(fraggment.task);
                        postDelayed(fraggment.task, delayTime);
                    }
                }
            }
        }
        public MyHandler(ViewPagerFragment fragment){
            weakReference=new WeakReference<ViewPagerFragment>(fragment);
        }
        private WeakReference<ViewPagerFragment> weakReference;
    }
    final  Runnable task=new Runnable() {
        @Override
        public void run() {
            if(isWheel) {
                //避免手动滑动后，马上翻页，应该等到下一个延迟时间才自动滑动
                if (System.currentTimeMillis() - lastTouchTime > delayTime - 500) {
                    mHandler.sendEmptyMessage(WHEEL);
                } else {
                    mHandler.sendEmptyMessage(WHEEL_WAIT);
                }
            }
        }
    };
    private MyHandler mHandler=new MyHandler(this);
    public void setWheel(boolean isWheel){
        this.isWheel=isWheel;
        if(isWheel){
            mHandler.removeCallbacks(task);
            mHandler.postDelayed(task,delayTime);
        }
    }

    private   class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=new ImageView(getContext());

            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            AdvInfo item= list.get(position);
            if(item.getRes()!=null) {
                Glide.with(getContext())
                        .load(item.getRes())
                        .into(imageView);
                container.addView(imageView);
            }
            else{
                Glide.with(getContext())
                        .load(item.getUrl())
                        .into(imageView);
                container.addView(imageView);
            }
            return imageView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
