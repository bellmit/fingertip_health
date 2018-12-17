package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

import com.jqsoft.fingertip_health.feature.IDateRange;

import java.util.Locale;

/**
 * 季度统计区间
 * Created by Administrator on 2018-01-01.
 */

public class QuarterTextBean implements IDateRange{
    public static final int NUMBER = 4;//最近NUMBER个季度
    private String presentation;//展现出来的文本
    private int startYearInt, endYearInt;
    private int startMonthInt, endMonthInt;//month从0开始
    private int beginDateInt, endDateInt;
    public QuarterTextBean() {
        super();
    }

    public QuarterTextBean(String presentation, int startYearInt, int endYearInt, int startMonthInt, int endMonthInt, int beginDateInt, int endDateInt) {
        super();
        this.presentation = presentation;
        this.startYearInt = startYearInt;
        this.endYearInt = endYearInt;
        this.startMonthInt = startMonthInt;
        this.endMonthInt = endMonthInt;
        this.beginDateInt = beginDateInt;
        this.endDateInt = endDateInt;
    }

    public String getPresentation() {
        return presentation;
    }

    @Override
    public String getStartDateString() {
        String startDate = String.format(Locale.CHINA, "%d-%02d-%02d", startYearInt, startMonthInt +1, beginDateInt);
        return startDate;
    }

    @Override
    public String getEndDateString() {
        String endDate = String.format(Locale.CHINA, "%d-%02d-%02d", endYearInt, endMonthInt +1, endDateInt);
        return endDate;
    }

    @Override
    public String getMonth() {
        String result = String.format(Locale.CHINA, "%d%02d", startYearInt, startMonthInt+1);
        return result;
    }

    @Override
    public String getYear() {
        String result = String.format(Locale.CHINA, "%d", startYearInt);
        return result;
    }



    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }


}
