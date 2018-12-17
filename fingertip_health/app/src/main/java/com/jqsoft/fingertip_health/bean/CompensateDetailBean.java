package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-05-25.
 */

public class CompensateDetailBean {
    private String name;
    private String idNumber;
    private int gender;
    private int hospitalImageId;
    private String hospitalName;
    private String department;
    private String beginDate;
    private String endDate;
    private String operation;
    private String totalFee;
    private String canCompensateFee;
    private String actualCompensateFee;
    private String fund;//农合基金
    private String familyAccumulation;
    private String individualAccumulation;

    public CompensateDetailBean(String name, String idNumber, int gender, int hospitalImageId, String hospitalName, String department, String beginDate, String endDate, String operation, String totalFee, String canCompensateFee, String actualCompensateFee, String fund, String familyAccumulation, String individualAccumulation) {
        this.name = name;
        this.idNumber = idNumber;
        this.gender = gender;
        this.hospitalImageId = hospitalImageId;
        this.hospitalName = hospitalName;
        this.department = department;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.operation = operation;
        this.totalFee = totalFee;
        this.canCompensateFee = canCompensateFee;
        this.actualCompensateFee = actualCompensateFee;
        this.fund = fund;
        this.familyAccumulation = familyAccumulation;
        this.individualAccumulation = individualAccumulation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getHospitalImageId() {
        return hospitalImageId;
    }

    public void setHospitalImageId(int hospitalImageId) {
        this.hospitalImageId = hospitalImageId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getCanCompensateFee() {
        return canCompensateFee;
    }

    public void setCanCompensateFee(String canCompensateFee) {
        this.canCompensateFee = canCompensateFee;
    }

    public String getActualCompensateFee() {
        return actualCompensateFee;
    }

    public void setActualCompensateFee(String actualCompensateFee) {
        this.actualCompensateFee = actualCompensateFee;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getFamilyAccumulation() {
        return familyAccumulation;
    }

    public void setFamilyAccumulation(String familyAccumulation) {
        this.familyAccumulation = familyAccumulation;
    }

    public String getIndividualAccumulation() {
        return individualAccumulation;
    }

    public void setIndividualAccumulation(String individualAccumulation) {
        this.individualAccumulation = individualAccumulation;
    }
}
