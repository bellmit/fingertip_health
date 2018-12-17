package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Administrator on 2018-03-15.
 */

public class NameValuePercentBean {
    private String name;
    private String value;
    private String percent;
    public NameValuePercentBean() {
        super();
    }

    public NameValuePercentBean(String name, String value, String percent) {
        this.name = name;
        this.value = value;
        this.percent = percent;
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

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

}
