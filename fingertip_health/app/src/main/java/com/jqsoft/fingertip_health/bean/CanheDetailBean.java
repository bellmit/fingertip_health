package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-05-25.
 */

public class CanheDetailBean {
    private int headImageResourceId;
    private String name;
    private int gender;
    private boolean hasCanhe;
    private String address;
    private String accumulatedOutpatient;
    private String accumulatedInhospital;
    private String collectMoneyTime;

    public CanheDetailBean(int headImageResourceId, String name, int gender, boolean hasCanhe, String address, String accumulatedOutpatient, String accumulatedInhospital, String collectMoneyTime) {
        this.headImageResourceId = headImageResourceId;
        this.name = name;
        this.gender = gender;
        this.hasCanhe = hasCanhe;
        this.address = address;
        this.accumulatedOutpatient = accumulatedOutpatient;
        this.accumulatedInhospital = accumulatedInhospital;
        this.collectMoneyTime = collectMoneyTime;
    }

    public int getHeadImageResourceId() {
        return headImageResourceId;
    }

    public void setHeadImageResourceId(int headImageResourceId) {
        this.headImageResourceId = headImageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isHasCanhe() {
        return hasCanhe;
    }

    public void setHasCanhe(boolean hasCanhe) {
        this.hasCanhe = hasCanhe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccumulatedOutpatient() {
        return accumulatedOutpatient;
    }

    public void setAccumulatedOutpatient(String accumulatedOutpatient) {
        this.accumulatedOutpatient = accumulatedOutpatient;
    }

    public String getAccumulatedInhospital() {
        return accumulatedInhospital;
    }

    public void setAccumulatedInhospital(String accumulatedInhospital) {
        this.accumulatedInhospital = accumulatedInhospital;
    }

    public String getCollectMoneyTime() {
        return collectMoneyTime;
    }

    public void setCollectMoneyTime(String collectMoneyTime) {
        this.collectMoneyTime = collectMoneyTime;
    }
}
