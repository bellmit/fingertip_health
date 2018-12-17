package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保变化情况趋势统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceArchiveTrendNaturalBean {
    private String monthName;
    private String householdNumber;
    private String personNumber;
    public SubsistenceArchiveTrendNaturalBean() {
        super();
    }

    public SubsistenceArchiveTrendNaturalBean(String monthName, String householdNumber, String personNumber) {
        this.monthName = monthName;
        this.householdNumber = householdNumber;
        this.personNumber = personNumber;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getHouseholdNumber() {
        return householdNumber;
    }

    public void setHouseholdNumber(String householdNumber) {
        this.householdNumber = householdNumber;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }
}
