package com.example.administrator.hei;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/25.
 */

public class LineTextView extends TextView {
    public LineTextView(Context context) {
        super(context);
        init();
    }
    private Paint mPaint;

    private void init() {
        mPaint=new Paint();
    }

    public LineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int count=attrs.getAttributeCount();
        boolean has=false;
        for (int i = 0; i < count; i++) {
            if(attrs.getAttributeName(i).equals("lineSpacingExtra")){
                has=true;
               break;
            }
        }
        if(!has){
            this.setLineSpacing(5.0f,1.0f);
        }
        init();
    }

    public LineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count=getLineCount();
        int lineHeight=getLineHeight();
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < count; i++) {
            if(count-1!=i)
                canvas.drawLine(getPaddingLeft(), getPaddingTop()+(i + 1) * lineHeight,
                        getWidth() - getPaddingRight(), getPaddingTop()+(i + 1) * lineHeight, mPaint);
            else{

                Rect r=new Rect();
                getLineBounds(i,r);
                TextPaint textPaint=this.getPaint();
                int length=textPaint.getTextWidths(getText().toString(),new float[getText().length()]);
                Toast.makeText(getContext(),""+length,Toast.LENGTH_SHORT).show();
                canvas.drawLine(getPaddingLeft(), getPaddingTop()+(i + 1) * lineHeight,
                        r.right, getPaddingTop()+(i + 1) * lineHeight, mPaint);

            }
        }
    }
}
