package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

import com.jqsoft.fingertip_health.feature.IDateRange;

import java.util.Locale;

/**
 * 月份统计区间
 * Created by Administrator on 2018-01-01.
 */

public class MonthTextBean implements IDateRange{
    public static final int NUMBER = 12;//最近NUMBER个月
    public static final String FORMAT_STRING = "yyyy-MM";
    private String presentation;//展现出来的文本
    private int year, month;//month从0开始
    private int beginDateInt, endDateInt;
    public MonthTextBean() {
        super();
    }

    public MonthTextBean(String presentation, int year, int month, int beginDateInt, int endDateInt) {
        super();
        this.presentation = presentation;
        this.year = year;
        this.month = month;
        this.beginDateInt = beginDateInt;
        this.endDateInt = endDateInt;
    }

    public String getPresentation() {
        return presentation;
    }

    @Override
    public String getStartDateString() {
        String startDate = String.format(Locale.CHINA, "%d-%02d-%02d", year, month+1, beginDateInt);
        return startDate;
    }

    @Override
    public String getEndDateString() {
        String endDate = String.format(Locale.CHINA, "%d-%02d-%02d", year, month+1, endDateInt);
        return endDate;
    }

    @Override
    public String getMonth() {
        String result = String.format(Locale.CHINA, "%d%02d", year, month+1);
        return result;
    }

    @Override
    public String getYear() {
        String result = String.format(Locale.CHINA, "%d", year);
        return result;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }


}
