package com.jqsoft.fingertip_health.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jqsoft.fingertip_health.R;

/**
 * Created by Administrator on 2017-06-28.
 */

public class DonutProgressView extends View {
    public static final int DEFAULT_TEXT_SIZE = 16;
    public static final int DEFAULT_DONUT_WIDTH=8;

    public static final int RADIUS_ADJUST=12;

    private int backgroundColor;

    private int backgroundDonutColor;
    private int percentageDonutColor;
    private float percentage;


    private int donutWidth;

    private Paint mPaint;

    public DonutProgressView(Context context) {
        this(context, null);
    }

    public DonutProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonutProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DonutProgressView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.DonutProgressView_donut_progress_backgroundColor:
                    backgroundColor=a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.DonutProgressView_donut_progress_backgroundDonutColor:
                    backgroundDonutColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.DonutProgressView_donut_progress_percentageDonutColor:
                    percentageDonutColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.DonutProgressView_donut_progress_percentage:
                    percentage=a.getFloat(attr, 0);
                    break;
                case R.styleable.DonutProgressView_donut_progress_width:
                    donutWidth=a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DONUT_WIDTH, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }

        }
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(DEFAULT_TEXT_SIZE);
        mPaint.setColor(Color.WHITE);


    }

    public void setPercentageValue(float f){
        percentage=f;
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(backgroundColor);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int radius = Math.min(width, height)/2-RADIUS_ADJUST;

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(donutWidth);
        mPaint.setColor(backgroundDonutColor);
        canvas.drawCircle(width/2, height/2, radius, mPaint);

        mPaint.setColor(percentageDonutColor);
        float sweepAngle = 360*percentage;
        RectF rect = new RectF(width/2-radius, height/2-radius, width/2+radius, height/2+radius);
        canvas.drawArc(rect, -90, sweepAngle, false, mPaint);

    }




    private int getMinWidth(){
        int total = 40;
        int desired = (int) (getPaddingLeft() + total + getPaddingRight());
        return desired;
    }

    private int getMinHeight(){
        return getMinWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            int desired = (int) (getMinWidth());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            int desired = (int) (getMinHeight());
            height = desired;
        }



        setMeasuredDimension(width, height);
    }
}
