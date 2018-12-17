package com.jqsoft.fingertip_health.bean;




public class NameValueBeanWithNo implements IStringRepresentationAndValue {
    private String name;
    private String value;
    private boolean selected;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public NameValueBeanWithNo(String name, String value, String number, boolean selected) {
        this.name = name;
        this.value = value;
        this.selected = selected;
        this.number = number;
    }

    public NameValueBeanWithNo(String name, String value, String number) {
        this(name, value,number, false);
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
