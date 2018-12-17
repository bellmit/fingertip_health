package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/1.
 */

public class DetailBaseInfo {
    private String batchNo;
    private String name;
    private String sex;
    private String cardNo;
    private String nation;
    private String birthday;
    private String maritalStatus;
    private String familyType;
    private String registerType;
    private String  familyAddress;



    public DetailBaseInfo() {
    }

    public DetailBaseInfo(String batchNo, String name, String sex, String cardNo, String nation, String birthday, String maritalStatus, String familyType, String registerType,String familyAddress) {
        this.batchNo = batchNo;
        this.name = name;
        this.sex = sex;
        this.cardNo = cardNo;
        this.nation = nation;
        this.birthday = birthday;
        this.maritalStatus = maritalStatus;
        this.familyType = familyType;
        this.registerType = registerType;
        this.familyAddress = familyAddress;
    }


    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getFamilyType() {
        return familyType;
    }

    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }
    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }
}
