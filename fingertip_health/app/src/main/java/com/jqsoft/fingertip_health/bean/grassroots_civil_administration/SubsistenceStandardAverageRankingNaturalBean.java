package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 平均补助水平排名统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceStandardAverageRankingNaturalBean {
    private String districtName;
    private String averageMoneyPerMonthPerson;
    public SubsistenceStandardAverageRankingNaturalBean() {
        super();
    }

    public SubsistenceStandardAverageRankingNaturalBean(String districtName, String averageMoneyPerMonthPerson) {
        this.districtName = districtName;
        this.averageMoneyPerMonthPerson = averageMoneyPerMonthPerson;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAverageMoneyPerMonthPerson() {
        return averageMoneyPerMonthPerson;
    }

    public void setAverageMoneyPerMonthPerson(String averageMoneyPerMonthPerson) {
        this.averageMoneyPerMonthPerson = averageMoneyPerMonthPerson;
    }
}
