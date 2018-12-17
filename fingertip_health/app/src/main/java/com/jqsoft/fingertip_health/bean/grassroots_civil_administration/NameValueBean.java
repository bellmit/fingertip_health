package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import com.jqsoft.fingertip_health.bean.IStringRepresentationAndValue;

/**
 * Created by Administrator on 2018-01-15.
 */


public class NameValueBean implements IStringRepresentationAndValue {
    private String name;
    private String value;
    private boolean selected;

    public NameValueBean(String name, String value, boolean selected) {
        this.name = name;
        this.value = value;
        this.selected = selected;
    }

    public NameValueBean(String name, String value) {
        this(name, value, false);
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String getStringRepresentation() {
        return name;
    }

    @Override
    public String getStringValue() {
        return value;
    }


}
