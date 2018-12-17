package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 资金台账救助资金总支出增长率统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceAccountIncreaseRatioNaturalBean {
    private String monthName;
    private String mom;//环比
    private String yoy;//同比
    public SubsistenceAccountIncreaseRatioNaturalBean() {
        super();
    }

    public SubsistenceAccountIncreaseRatioNaturalBean(String monthName, String mom, String yoy) {
        this.monthName = monthName;
        this.mom = mom;
        this.yoy = yoy;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getMom() {
        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    public String getYoy() {
        return yoy;
    }

    public void setYoy(String yoy) {
        this.yoy = yoy;
    }
}
