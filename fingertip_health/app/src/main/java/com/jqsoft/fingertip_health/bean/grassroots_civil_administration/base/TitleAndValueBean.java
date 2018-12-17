package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

/**
 * Created by Administrator on 2018-01-19.
 */

public class TitleAndValueBean {
    private String title;//显示的值
    private String value;//值
    public TitleAndValueBean() {
        super();
    }

    public TitleAndValueBean(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
