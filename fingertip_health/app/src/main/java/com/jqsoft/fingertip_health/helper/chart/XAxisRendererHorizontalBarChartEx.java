package com.jqsoft.fingertip_health.helper.chart;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.renderer.XAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.util.Util;

/**
 * Created by Administrator on 2018-05-08.
 */

public class XAxisRendererHorizontalBarChartEx extends XAxisRendererHorizontalBarChart {
//    TextPaint textPaint;
    public XAxisRendererHorizontalBarChartEx(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans, BarChart chart) {
        super(viewPortHandler, xAxis, trans, chart);

//        initTextPaint();
    }

//    public void initTextPaint(){
//        textPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
//        textPaint.setDither(true);
////        textPaint.setTextSize(12);
////        textPaint.setColor(Color.BLACK);
//
//        textPaint.setTypeface(mXAxis.getTypeface());
//        textPaint.setTextSize(mXAxis.getTextSize());
//        textPaint.setColor(mXAxis.getTextColor());
//
//    }

    @Override
    public void renderAxisLabels(Canvas c) {
        super.renderAxisLabels(c);
    }

    @Override
    protected void drawLabels(Canvas c, float pos, MPPointF anchor) {
        super.drawLabels(c, pos, anchor);
    }

    @Override
    protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        if (!Util.isStringMultipleLine(formattedLabel)) {
            super.drawLabel(c, formattedLabel, x, y, anchor, angleDegrees);
        } else {
            drawMultipleLineLabel(c, formattedLabel, x, y, anchor, angleDegrees);


//            Rect bounds = new Rect();
//            textPaint.getTextBounds(formattedLabel, 0, formattedLabel.length(), bounds);
//            int width = bounds.width();
//            int height = bounds.height();
//            FSize fSize = FSize.getInstance(width, height);
//            Utils.drawMultilineText(c, formattedLabel, x, y, textPaint, fSize, anchor, angleDegrees);
////        Utils.drawXAxisValue(c, formattedLabel, x, y, mAxisLabelPaint, anchor, angleDegrees);
//            FSize.recycleInstance(fSize);
        }
    }

    protected void drawMultipleLineLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        float labelHeight = mXAxis.getTextSize();
//        float labelInterval = 18f;
        String[] labels = formattedLabel.split(Constants.NEW_LINE_SEPARATOR);

        Paint mFirstLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFirstLinePaint.setColor(mAxis.getTextColor());
        mFirstLinePaint.setTextAlign(Paint.Align.CENTER);
        mFirstLinePaint.setTextSize(mAxis.getTextSize());
        mFirstLinePaint.setTypeface(mXAxis.getTypeface());

//        Paint mSecondLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mSecondLinePaint.setColor(mAxis.getTextColor());
//        mSecondLinePaint.setTextAlign(Paint.Align.CENTER);
//        mSecondLinePaint.setTextSize(mAxis.getTextSize());
//        mSecondLinePaint.setTypeface(mXAxis.getTypeface());

        if (labels.length > 1) {
            Utils.drawXAxisValue(c, labels[0], x, y - labelHeight, mFirstLinePaint, anchor, angleDegrees);
            Utils.drawXAxisValue(c, labels[1], x, y + labelHeight, mFirstLinePaint, anchor, angleDegrees);
//            Utils.drawXAxisValue(c, labels[0], x, y, mFirstLinePaint, anchor, angleDegrees);
//            Utils.drawXAxisValue(c, labels[1], x, y + labelHeight + labelInterval, mFirstLinePaint, anchor, angleDegrees);
        } else {
            Utils.drawXAxisValue(c, formattedLabel, x, y, mFirstLinePaint, anchor, angleDegrees);
        }
    }

}
