package com.jqsoft.fingertip_health.helper.chart;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.YAxis;

/**
 * Created by Administrator on 2018-05-08.
 */

public class HorizontalBarChartEx extends HorizontalBarChart {
    public HorizontalBarChartEx(Context context) {
        super(context);
    }

    public HorizontalBarChartEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalBarChartEx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        XAxisRendererHorizontalBarChartEx renderer = new XAxisRendererHorizontalBarChartEx(getViewPortHandler(), getXAxis(), getTransformer(YAxis.AxisDependency.LEFT), this);
        setXAxisRenderer(renderer);
    }
}
