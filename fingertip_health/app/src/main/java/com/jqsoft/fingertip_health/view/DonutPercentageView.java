package com.jqsoft.fingertip_health.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jqsoft.fingertip_health.R;

import java.util.Locale;

/**
 * Created by Administrator on 2017-06-28.
 */

public class DonutPercentageView extends View {
    public static final int DEFAULT_TEXT_SIZE = 16;
    public static final int DEFAULT_DONUT_WIDTH=8;
    public static final int DEFAULT_PERCENTAGE_LINE_LENGTH=100;
    public static final int DEFAULT_LINE_OFFSET=20;
    
    public static final int RADIUS_ADJUST=120;

    private int backgroundColor;

    private int backgroundDonutColor;
    private int percentageDonutColor;
    private int textColor;
    private float percentage;

    private String value;
    private String hint;

    private int valueTextSize;
    private int hintTextSize;
    private int percentageTextSize;

    private int donutWidth;

    private Paint mPaint;

    public DonutPercentageView(Context context) {
        this(context, null);
    }

    public DonutPercentageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonutPercentageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DonutPercentageView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.DonutPercentageView_donut_backgroundColor:
                    backgroundColor=a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.DonutPercentageView_donut_backgroundDonutColor:
                    backgroundDonutColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.DonutPercentageView_donut_percentageDonutColor:
                    percentageDonutColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.DonutPercentageView_donut_text_color:
                    textColor=a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.DonutPercentageView_donut_percentage:
                    percentage=a.getFloat(attr, 0);
                    break;
                case R.styleable.DonutPercentageView_donut_value:
                    value=a.getString(attr);
                    break;
                case R.styleable.DonutPercentageView_donut_hint:
                    hint=a.getString(attr);
                    break;
                case R.styleable.DonutPercentageView_donut_value_text_size:
                    valueTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.DonutPercentageView_donut_hint_text_size:
                    hintTextSize=a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.DonutPercentageView_donut_percentage_text_size:
                    percentageTextSize=a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.DonutPercentageView_donut_width:
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

//        mBound = new Rect();
//        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

    }

    public void setPercentageValue(float f){
        percentage=f;
        postInvalidate();
    }

    public void setValue(String v){
        value=v;
        postInvalidate();
    }

    public void setHint(String h){
        hint=h;
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

        int valueWidth = getTextMinWidth(value, valueTextSize);
        int valueHeight = getTextMinHeight(value, valueTextSize);
//        RectF valueRect = new RectF(width/2-valueWidth/2, height/2-valueHeight/2, width/2+valueWidth/2, height/2+valueHeight/2);
        mPaint.setColor(textColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(valueTextSize);
        canvas.drawText(value, width/2-valueWidth/2, height/2-valueHeight, mPaint);

        int hintWidth = getTextMinWidth(hint, hintTextSize);
        int hintHeight = getTextMinHeight(hint, hintTextSize);
        mPaint.setColor(textColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(4);
        mPaint.setTextSize(hintTextSize);
        canvas.drawText(hint, width/2-hintWidth/2, height/2+hintHeight, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        float lineStartX = getPercentageLineStartX();
        float lineStartY = getPercentageLineStartY();
        float lineEndX = lineStartX+DEFAULT_PERCENTAGE_LINE_LENGTH;
        float lineEndY = lineStartY;
        canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, mPaint);

        String percentageString = getPercentageStringFromFloat(percentage);
        int percentageWidth = getTextMinWidth(percentageString, percentageTextSize);
        int percentageHeight = getTextMinHeight(percentageString, percentageTextSize);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(percentageTextSize);
        float percentageTopX = lineEndX+DEFAULT_LINE_OFFSET;
        float percentageTopY = lineEndY;
        canvas.drawText(percentageString, percentageTopX, percentageTopY, mPaint);
    }

    private float getPercentageLineStartX(){
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int radius = Math.min(width, height)/2-RADIUS_ADJUST;

        float angle = getPercentageLineAngle();
        float radian = (float) Math.toRadians(-90+angle);
        float x = (float) (width/2+Math.cos(radian)*radius);
        return x;
    }

    private float getPercentageLineStartY(){
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int radius = Math.min(width, height)/2-RADIUS_ADJUST;

        float angle = getPercentageLineAngle();
        float radian = (float) Math.toRadians(-90+angle);
        float y = (float) (height/2+Math.sin(radian)*radius);
        return y;
    }

    private float getPercentageLineAngle(){
        float angle ;
        if (percentage<0.3){
            angle=30/2;
        } else {
            angle=30;
        }
        return angle;
    }

    private String getPercentageStringFromFloat(float f){
        float r = f*100;
        String result = String.format(Locale.CHINA, "%.2f%%", r);
        return result;
    }

    private int getTextMinWidth(String text, int textSize){
        mPaint.setTextSize(textSize);
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        float textWidth = rect.width();
        return (int)textWidth;
    }

    private int getTextMinHeight(String text, int textSize){
        mPaint.setTextSize(textSize);
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        float textHeight = rect.height();
        return (int)textHeight;
    }

    private int getMinWidth(){
        int valueTextWidth = getTextMinWidth(value, valueTextSize);
        int hintTextWidth = getTextMinWidth(hint, hintTextSize);
        int maxOfValueAndHint = Math.max(valueTextWidth, hintTextWidth);
        int twoDonutWidth = 2*donutWidth;
        String percentageString = getPercentageStringFromFloat(percentage);
        int percentageWidth = getTextMinWidth(percentageString, percentageTextSize);
        int extra = 30;
        int total = maxOfValueAndHint+twoDonutWidth+percentageWidth+DEFAULT_PERCENTAGE_LINE_LENGTH+extra;
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
