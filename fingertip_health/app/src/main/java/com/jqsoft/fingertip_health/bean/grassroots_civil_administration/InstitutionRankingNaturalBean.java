package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 供养机构排名统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class InstitutionRankingNaturalBean {
    private String districtName;
    private String institutionNumber;
    private String bedNumber;
    public InstitutionRankingNaturalBean() {
        super();
    }

    public InstitutionRankingNaturalBean(String districtName, String institutionNumber, String bedNumber) {
        this.districtName = districtName;
        this.institutionNumber = institutionNumber;
        this.bedNumber = bedNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getInstitutionNumber() {
        return institutionNumber;
    }

    public void setInstitutionNumber(String institutionNumber) {
        this.institutionNumber = institutionNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
}
