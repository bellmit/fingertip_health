package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保档案排名统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceArchiveRankingNaturalBean {
    private String districtName;
    private String householdNumber;
    private String personNumber;
    public SubsistenceArchiveRankingNaturalBean() {
        super();
    }

    public SubsistenceArchiveRankingNaturalBean(String districtName, String householdNumber, String personNumber) {
        this.districtName = districtName;
        this.householdNumber = householdNumber;
        this.personNumber = personNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
