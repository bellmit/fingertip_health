package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 金额支出占比统计/救助人次占比统计
 * Created by Administrator on 2018-01-11.
 */

public class TempDisasterAssistancePercentageBean {
    private String name;
    private String value;
    public TempDisasterAssistancePercentageBean() {
        super();
    }

    public TempDisasterAssistancePercentageBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
