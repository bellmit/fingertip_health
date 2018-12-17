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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DonutDivisionPercentageView extends View {
    public static final int DEFAULT_TEXT_SIZE = 16;
    public static final int DEFAULT_DONUT_WIDTH=8;

    public static final int RADIUS_ADJUST=60;

    private int backgroundColor;

    private int backgroundDonutColor;
    private int textColor;

    private String value;
    private String hint;

    private int valueTextSize;
    private int hintTextSize;

    private int donutWidth;

    private List<Float> numberList=new ArrayList<>();
    private List<Integer> colorList = new ArrayList<>();

    private List<Float> percentageList=new ArrayList<>();

    private Paint mPaint;

    public DonutDivisionPercentageView(Context context) {
        this(context, null);
    }

    public DonutDivisionPercentageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonutDivisionPercentageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DonutDivisionPercentageView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.DonutDivisionPercentageView_donut_division_backgroundColor:
                    backgroundColor=a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.DonutDivisionPercentageView_donut_division_backgroundDonutColor:
                    backgroundDonutColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.DonutDivisionPercentageView_donut_division_text_color:
                    textColor=a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.DonutDivisionPercentageView_donut_division_value:
                    value=a.getString(attr);
                    break;
                case R.styleable.DonutDivisionPercentageView_donut_division_hint:
                    hint=a.getString(attr);
                    break;
                case R.styleable.DonutDivisionPercentageView_donut_division_value_text_size:
                    valueTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.DonutDivisionPercentageView_donut_division_hint_text_size:
                    hintTextSize=a.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.DonutDivisionPercentageView_donut_division_width:
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

    public void populatePercentage(){
        try {
            if (this.numberList!=null&&this.numberList.size()>0){
                percentageList.clear();
                Float sum = 0f;
                for (int i = 0; i < this.numberList.size(); ++i){
                    sum+=this.numberList.get(i);
                }
                for (int i = 0; i < this.numberList.size(); ++i){
                    Float v = this.numberList.get(i);
                    Float f = v/sum;
                    percentageList.add(f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setNumberList(List<Float> numberList){
        this.numberList=numberList;
        populatePercentage();
        postInvalidate();
    }

    public void setColorList(List<Integer> colorList){
        this.colorList=colorList;
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
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeWidth(donutWidth);
        mPaint.setColor(backgroundDonutColor);
        canvas.drawCircle(width/2, height/2, radius, mPaint);

        float startAngle = -90;
        for (int i = 0; i < numberList.size(); ++i) {
            float percent = percentageList.get(i);
            int percentageDonutColor = colorList.get(i);
            mPaint.setColor(percentageDonutColor);
            float sweepAngle = 360*percent;
            RectF rect = new RectF(width/2-radius, height/2-radius, width/2+radius, height/2+radius);
            canvas.drawArc(rect, startAngle, sweepAngle, false, mPaint);
            startAngle+=sweepAngle;
        }

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
        int extra = 30;
        int total = maxOfValueAndHint+twoDonutWidth+extra;
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
