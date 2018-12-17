package com.jqsoft.fingertip_health.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jqsoft.fingertip_health.utils.DensityUtil;


public class RectanglePercentageView extends View {
    private int mWidth;
    private RectF mRect1;
    private RectF mRect2;
    private Paint mPaint;
    private int [] mColors = new int[]{Color.parseColor("#00FF00")};
    private static final int BG_COLOR = Color.parseColor("#FFFFFF");
    private int barHeight = 10;

    private double percentage;
    public RectanglePercentageView(Context context) {
        super(context);
        init(context);
    }

    public RectanglePercentageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RectanglePercentageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        setWillNotDraw(false);
        int height = DensityUtil.dip2px(barHeight);
        mWidth = context.getResources().getDisplayMetrics().widthPixels-DensityUtil.dip2px(40);
        mRect1 = new RectF(0,(getMeasuredHeight()-height)/2,0, height);
        mRect2 = new RectF(0,0,0, height);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(BG_COLOR);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        int height = DensityUtil.dip2px(barHeight);
        canvas.drawRect(0,(getMeasuredHeight()-height)/2,mWidth,(getMeasuredHeight()+height)/2, mPaint);
        mPaint.setColor(mColors[0]);
        mPaint.setStyle(Paint.Style.FILL);
        mRect1=new RectF(0,(getMeasuredHeight()-height)/2, (int) (mWidth*percentage),(getMeasuredHeight()+height)/2);
        canvas.drawRect(mRect1,mPaint);
    }

    /**
     * 给数据赋值
     * @param percentage
     */
    public void setPercentage(double percentage){
        this.percentage=percentage;
        int height = DensityUtil.dip2px(barHeight);
        mRect1.left=0;
        mRect1.top=(getMeasuredHeight()-height)/2;
        mRect1.right = (int) (mWidth*percentage);
        mRect1.bottom=(getMeasuredHeight()+height)/2;
        invalidate();
    }
}