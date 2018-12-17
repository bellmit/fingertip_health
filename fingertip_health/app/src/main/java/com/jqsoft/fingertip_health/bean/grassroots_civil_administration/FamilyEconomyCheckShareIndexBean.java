package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 家庭经济状况核对-信息共享指标统计
 * Created by Administrator on 2018-01-15.
 */

public class FamilyEconomyCheckShareIndexBean {
    private String title;
    private String firstValue;
    public FamilyEconomyCheckShareIndexBean() {
        super();
    }

    public FamilyEconomyCheckShareIndexBean(String title, String firstValue) {
        this.title = title;
        this.firstValue = firstValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }
}
