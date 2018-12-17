package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

import com.jqsoft.fingertip_health.feature.IDateRange;

import java.util.Locale;

/**
 * 年度统计区间
 * Created by Administrator on 2018-01-01.
 */

public class YearTextBean implements IDateRange{
    public static final int NUMBER = 19;//最近NUMBER年
    private String presentation;//展现出来的文本
    private int startYearInt;

    public YearTextBean() {
        super();
    }

    public YearTextBean(String presentation, int startYearInt) {
        super();
        this.presentation = presentation;
        this.startYearInt = startYearInt;
    }

    @Override
    public String getPresentation() {
        return presentation;
    }

    @Override
    public String getStartDateString() {
        String s = String.format(Locale.CHINA, "%d-%02d-%02d", startYearInt, 1, 1);
        return s;
    }

    @Override
    public String getEndDateString() {
        String s = String.format(Locale.CHINA, "%d-%02d-%02d", startYearInt, 12, 31);
        return s;
    }

    @Override
    public String getMonth() {
        String result = String.format(Locale.CHINA, "%d%02d", startYearInt, 1);
        return result;
    }

    @Override
    public String getYear() {
        String result = String.format(Locale.CHINA, "%d", startYearInt);
        return result;
    }


}
