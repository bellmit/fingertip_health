package com.jqsoft.fingertip_health.helper.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-01-17.
 */

public class PercentAndLabelFormatter extends PercentFormatter {
    boolean showAll=true;
    List<Float> floatList=new ArrayList<>();
    public PercentAndLabelFormatter(boolean showAll, List<Float> floatList) {
        this.showAll=showAll;
        this.floatList=floatList;
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        String label = Constants.EMPTY_STRING;
        float v = 0f;
        if (entry instanceof PieEntry){
            PieEntry pe = (PieEntry)entry;
            label = pe.getLabel();
            v=pe.getValue();
        }
        String combinedLabel = Constants.EMPTY_STRING;
        if (StringUtils.isBlank(label)){

        } else {
            combinedLabel = Constants.SPACE_STRING + label;
        }
        boolean isTiny;
        if (showAll){
            isTiny=false;
        } else {
            isTiny =  Util.isItemPercentVerySmall(v, floatList);
        }
        if (!isTiny){
            return super.getFormattedValue(value, entry, dataSetIndex, viewPortHandler);
//            return super.getFormattedValue(value, entry, dataSetIndex, viewPortHandler) + combinedLabel;
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
//        String label = axis.getFormattedLabel(0);
//        return super.getFormattedValue(value, axis) + Constants.SPACE_STRING + label;
        return super.getFormattedValue(value, axis);
    }
}
