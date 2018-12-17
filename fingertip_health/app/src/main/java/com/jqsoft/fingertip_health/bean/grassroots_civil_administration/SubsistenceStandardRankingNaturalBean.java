package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 标准排名统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceStandardRankingNaturalBean {
    private String districtName;
    private String moneyPerMonthPerson;
    public SubsistenceStandardRankingNaturalBean() {
        super();
    }

    public SubsistenceStandardRankingNaturalBean(String districtName, String moneyPerMonthPerson) {
        this.districtName = districtName;
        this.moneyPerMonthPerson = moneyPerMonthPerson;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getMoneyPerMonthPerson() {
        return moneyPerMonthPerson;
    }

    public void setMoneyPerMonthPerson(String moneyPerMonthPerson) {
        this.moneyPerMonthPerson = moneyPerMonthPerson;
    }
}
