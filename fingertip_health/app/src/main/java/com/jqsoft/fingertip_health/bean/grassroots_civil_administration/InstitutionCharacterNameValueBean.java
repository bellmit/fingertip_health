package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Administrator on 2018-03-15.
 */

public class InstitutionCharacterNameValueBean {
    private String name;
    private String value;
    private String percent;
    private String bednum;
    public InstitutionCharacterNameValueBean() {
        super();
    }

    public InstitutionCharacterNameValueBean(String name, String value, String percent, String bednum) {
        this.name = name;
        this.value = value;
        this.percent = percent;
        this.bednum = bednum;
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

    public String getBednum() {
        return bednum;
    }

    public void setBednum(String bednum) {
        this.bednum = bednum;
    }
}
