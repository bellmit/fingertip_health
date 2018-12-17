package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 资金台账趋势统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceAccountTrendNaturalBean {
    private String monthName;
    private String money;
    public SubsistenceAccountTrendNaturalBean() {
        super();
    }

    public SubsistenceAccountTrendNaturalBean(String monthName, String money) {
        this.monthName = monthName;
        this.money = money;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
