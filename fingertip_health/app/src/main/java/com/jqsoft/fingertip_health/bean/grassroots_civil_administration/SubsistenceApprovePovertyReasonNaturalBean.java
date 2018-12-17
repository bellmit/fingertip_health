package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保审批情况/特困人员供养/低收入家庭 致贫原因/退保注销原因 分类统计可读bean
 * Created by Administrator on 2018-01-09.
 */

public class SubsistenceApprovePovertyReasonNaturalBean {
    private String title;
    private String householdNumber;
    private float percent;
    public SubsistenceApprovePovertyReasonNaturalBean() {
        super();
    }

    public SubsistenceApprovePovertyReasonNaturalBean(String title, String householdNumber, float percent) {
        this.title = title;
        this.householdNumber = householdNumber;
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHouseholdNumber() {
        return householdNumber;
    }

    public void setHouseholdNumber(String householdNumber) {
        this.householdNumber = householdNumber;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
