package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;


public class UseDrugInfo implements Serializable{

    private String name;//  药物名称	String
    private String frequency;// 频次（每日多少次	String
    private String singleDose ;//  单次剂量（每次多少剂量）	Double
    private String unit;// 计量单位	String
    private String drugUsage;

    public UseDrugInfo(String name, String frequency, String singleDose, String unit, String drugUsage) {
        this.name = name;
        this.frequency = frequency;
        this.singleDose = singleDose;
        this.unit = unit;
        this.drugUsage = drugUsage;
    }

    public UseDrugInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSingleDose() {
        return singleDose;
    }

    public void setSingleDose(String singleDose) {
        this.singleDose = singleDose;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDrugUsage() {
        return drugUsage;
    }

    public void setDrugUsage(String drugUsage) {
        this.drugUsage = drugUsage;
    }
}
