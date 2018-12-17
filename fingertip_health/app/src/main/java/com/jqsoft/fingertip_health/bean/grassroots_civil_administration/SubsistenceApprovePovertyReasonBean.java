package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保审批情况致贫原因分类统计
 * Created by Administrator on 2018-01-09.
 */

public class SubsistenceApprovePovertyReasonBean {
    private String value;
    private float percentval;
    private String name;
    public SubsistenceApprovePovertyReasonBean() {
        super();
    }

    public SubsistenceApprovePovertyReasonBean(String value, float percentval, String name) {
        this.value = value;
        this.percentval = percentval;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public float getPercentval() {
        return percentval;
    }

    public void setPercentval(float percentval) {
        this.percentval = percentval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
