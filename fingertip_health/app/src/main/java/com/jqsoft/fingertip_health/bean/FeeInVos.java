package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/11.
 */

public class FeeInVos implements Serializable {
    private String chargeItemsCode;//收费项目编码	String	N
    private String implementDoctorCode;//	执行医生编号	String		（不传）
    private String implementDoctorName;//执行医生姓名	String		（不传）
    private String implementDepartmentCode;//	执行科室	String		（不传）
    private String implementDepartmentName;//执行科室名称	String		（不传）
    private String implementSign;//	执行标志	Integer		（不传）0未执行 1已执行
    private String implementTime;//执行时间	String		（不传）
    private String chargeFrequency;//收费次数	Double	N
    private String chargeCompany;//收费单位	String
    private String chargeTicketprice;//	收费票价	Double	N
    private String feeTotal;//	总金额	Double	N
    private String isReim;//	医保范围	String	N	保内、保外
    private String feePersonal;//个人金额	String		（不传）
    private String feePre;//	先付金额	String		（不传）
    private String feePublic;//	公费金额	String		（不传）

    public FeeInVos() {
    }

    public FeeInVos(String chargeItemsCode, String implementDoctorCode, String implementDoctorName, String implementDepartmentCode, String implementDepartmentName, String implementSign, String implementTime, String chargeFrequency, String chargeCompany, String chargeTicketprice, String feeTotal, String isReim, String feePersonal, String feePre, String feePublic) {
        this.chargeItemsCode = chargeItemsCode;
        this.implementDoctorCode = implementDoctorCode;
        this.implementDoctorName = implementDoctorName;
        this.implementDepartmentCode = implementDepartmentCode;
        this.implementDepartmentName = implementDepartmentName;
        this.implementSign = implementSign;
        this.implementTime = implementTime;
        this.chargeFrequency = chargeFrequency;
        this.chargeCompany = chargeCompany;
        this.chargeTicketprice = chargeTicketprice;
        this.feeTotal = feeTotal;
        this.isReim = isReim;
        this.feePersonal = feePersonal;
        this.feePre = feePre;
        this.feePublic = feePublic;
    }

    public String getChargeItemsCode() {
        return chargeItemsCode;
    }

    public void setChargeItemsCode(String chargeItemsCode) {
        this.chargeItemsCode = chargeItemsCode;
    }

    public String getImplementDoctorCode() {
        return implementDoctorCode;
    }

    public void setImplementDoctorCode(String implementDoctorCode) {
        this.implementDoctorCode = implementDoctorCode;
    }

    public String getImplementDoctorName() {
        return implementDoctorName;
    }

    public void setImplementDoctorName(String implementDoctorName) {
        this.implementDoctorName = implementDoctorName;
    }

    public String getImplementDepartmentCode() {
        return implementDepartmentCode;
    }

    public void setImplementDepartmentCode(String implementDepartmentCode) {
        this.implementDepartmentCode = implementDepartmentCode;
    }

    public String getImplementDepartmentName() {
        return implementDepartmentName;
    }

    public void setImplementDepartmentName(String implementDepartmentName) {
        this.implementDepartmentName = implementDepartmentName;
    }

    public String getImplementSign() {
        return implementSign;
    }

    public void setImplementSign(String implementSign) {
        this.implementSign = implementSign;
    }

    public String getImplementTime() {
        return implementTime;
    }

    public void setImplementTime(String implementTime) {
        this.implementTime = implementTime;
    }

    public String getChargeFrequency() {
        return chargeFrequency;
    }

    public void setChargeFrequency(String chargeFrequency) {
        this.chargeFrequency = chargeFrequency;
    }

    public String getChargeCompany() {
        return chargeCompany;
    }

    public void setChargeCompany(String chargeCompany) {
        this.chargeCompany = chargeCompany;
    }

    public String getChargeTicketprice() {
        return chargeTicketprice;
    }

    public void setChargeTicketprice(String chargeTicketprice) {
        this.chargeTicketprice = chargeTicketprice;
    }

    public String getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(String feeTotal) {
        this.feeTotal = feeTotal;
    }

    public String getIsReim() {
        return isReim;
    }

    public void setIsReim(String isReim) {
        this.isReim = isReim;
    }

    public String getFeePersonal() {
        return feePersonal;
    }

    public void setFeePersonal(String feePersonal) {
        this.feePersonal = feePersonal;
    }

    public String getFeePre() {
        return feePre;
    }

    public void setFeePre(String feePre) {
        this.feePre = feePre;
    }

    public String getFeePublic() {
        return feePublic;
    }

    public void setFeePublic(String feePublic) {
        this.feePublic = feePublic;
    }
}
