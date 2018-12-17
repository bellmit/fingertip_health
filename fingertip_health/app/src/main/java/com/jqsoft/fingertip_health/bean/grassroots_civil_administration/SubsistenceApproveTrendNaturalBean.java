package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保审批情况趋势统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceApproveTrendNaturalBean {
    private String monthName;
    private String throughRatio;
    public SubsistenceApproveTrendNaturalBean() {
        super();
    }

    public SubsistenceApproveTrendNaturalBean(String monthName, String throughRatio) {
        this.monthName = monthName;
        this.throughRatio = throughRatio;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getThroughRatio() {
        return throughRatio;
    }

    public void setThroughRatio(String throughRatio) {
        this.throughRatio = throughRatio;
    }
}
