package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保审批情况排名统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceApproveRankingNaturalBean {
    private String districtName;
    private String throughRatio;
    public SubsistenceApproveRankingNaturalBean() {
        super();
    }

    public SubsistenceApproveRankingNaturalBean(String districtName, String throughRatio) {
        this.districtName = districtName;
        this.throughRatio = throughRatio;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getThroughRatio() {
        return throughRatio;
    }

    public void setThroughRatio(String throughRatio) {
        this.throughRatio = throughRatio;
    }
}
