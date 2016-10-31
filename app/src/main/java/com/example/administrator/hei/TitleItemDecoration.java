package com.example.administrator.hei;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {
    private int mheight=20;
    private List<Bean> mData;
    private Paint mPaint;
    private Rect mBound;
    private Context mContext;

    public TitleItemDecoration(Context context,List<Bean> mData) {
        super();
        this.mData = mData;
        mPaint=new Paint();
        mBound=new Rect();
        mContext=context;
        getPx();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position=((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        if (position>-1){
            if(position==0){
                outRect.set(0,mheight,0,0);
            }
            else{
                if(null!=mData.get(position).getTag()&&!mData.get(position).getTag()
                        .equals(mData.get(position-1).getTag())){
                    outRect.set(0,mheight,0,0);
                }
                else{
                    outRect.set(0,0,0,0);
                }
            }
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left=parent.getPaddingLeft();
        final int right=parent.getWidth()-parent.getPaddingRight();
        final int count=parent.getChildCount();
        for (int i = 0; i < count; i++) {
            final View child=parent.getChildAt(i);
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();
            int position=params.getViewLayoutPosition();
            if(position>=-1){
                if(position==0){
                    drawTitleArea(c,left,right,child,params,position);
                }else if(null!=mData.get(position).getTag()&&
                        !mData.get(position).getTag().equals(mData.get(position-1).getTag())
                        ){
                    //tag不为空且与前一个的tag不等则要绘制头部
                    drawTitleArea(c,left,right,child,params,position);
                }
            }
        }
    }

    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {
        mPaint.setColor(Color.parseColor("#ff3333"));
        c.drawRect(left,child.getTop()-mheight-params.topMargin,right,child.getTop()-params.topMargin,mPaint);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.getTextBounds(mData.get(position).getTag(),0,mData.get(position).getTag().length(),mBound);
        c.drawText(mData.get(position).getTag(),child.getPaddingLeft(),child.getTop()-params.topMargin
                -(mheight-mBound.height())/2,mPaint);
    }

    private void getPx(){
        mheight= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mheight,mContext.getResources().getDisplayMetrics());
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int position=((LinearLayoutManager)parent.getLayoutManager()).findFirstVisibleItemPosition();
        String tag=mData.get(position).getTag();
        View child=parent.findViewHolderForLayoutPosition(position).itemView;
        mPaint.setColor(Color.parseColor("#ff3333"));
        c.drawRect(parent.getPaddingLeft(),parent.getPaddingTop(),parent.getRight()-parent.getPaddingRight()
                ,parent.getPaddingTop()+mheight,mPaint);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.getTextBounds(mData.get(position).getTag(),0,mData.get(position).getTag().length(),mBound);
        c.drawText(mData.get(position).getTag(),child.getPaddingLeft(),parent.getPaddingTop()+mheight-(mheight-mBound.height())/2,mPaint);
    }
}
