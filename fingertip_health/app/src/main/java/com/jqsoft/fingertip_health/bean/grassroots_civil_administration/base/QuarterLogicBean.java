package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

import com.jqsoft.fingertip_health.util.Util;

import java.util.Locale;

/**
 * Created by Administrator on 2018-01-01.
 */

public class QuarterLogicBean {
    private int year;
    private int quarter;//季度值,从0开始
    public QuarterLogicBean() {
        super();
    }

    public QuarterLogicBean(int year, int quarter) {
        this.year = year;
        this.quarter = quarter;
    }

    public QuarterLogicBean getPreviousNumberBean(int offset){
        int newYear = year;
        int newQuarter = quarter;
        for (int i = 0; i < offset; ++i){
            if (newQuarter == 0){
                --newYear;
                newQuarter=3;
            } else {
                --newQuarter;
            }
        }
        QuarterLogicBean result = new QuarterLogicBean(newYear, newQuarter);
        return result;
    }

    public QuarterTextBean toQuarterTextBean(){
        String presentation = String.format(Locale.CHINA, "%d年第%d季度", year, (quarter+1));
        int startMonthInt = Util.getStartMonthIntFromQuarter(quarter);
        int endMonthInt = Util.getEndMonthIntFromQuarter(quarter);
        int endDateInt = Util.getMonthMaxDateFromYearAndMonth(year, endMonthInt);
        QuarterTextBean result = new QuarterTextBean(presentation, year, year,  startMonthInt, endMonthInt, 1, endDateInt);
        return result;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }
}
