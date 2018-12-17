package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/14.
 */

public class SelectOutPatientChargesBean implements Serializable {
    private String sn;//	序号	N	int
    private String organizationCode;//机构代码	N	String
    private String visitNumber;//就诊号	N	String
    private String idNumber;//
    private String age;//年龄		String
    private String archivesNumber;//档案编号		String
    private String birthdate;//
    private String cardNumber;//医疗证号		String
    private String patientType;//	病人类型		String
    private String name;//	姓名		String
    private String parentName;//	父母姓名		String
    private String visitType;//	就诊类型		String	0 初诊 1 复诊
    private String occupationName;//职业名称		String
    private String residentialAddress;//居住地址		String
    private String phoneNumber;//
    private String diagnosisName;//诊断名称		String
    private String outpatientDoctorName;//医生名称		String
    private String sbp;//收缩压		Integer
    private String dbp;//	舒张压		Integer
    private String t;//温度		Double
    private String glu;//血糖值		Double
    private String invoiceNumber;//发票号		String
    private String StringinvoiceTime;//收费时间		String
    private String visitTime;//就诊时间		String
    private String squareDepartmentName;//开方医生姓名		String
    private String feeTotal;//	总费用		Double
    private String feeCms;//农合报销费用		Double
    private String feeCms_card;//医保报销费用		Double
    private String feeYs;//应收（个人支付）		Double
    private String feeRounding;//舍入金额		Double
    private  String invalidSign;
    public SelectOutPatientChargesBean() {
    }

    public SelectOutPatientChargesBean(String sn, String organizationCode, String visitNumber, String idNumber, String age, String archivesNumber, String birthdate, String cardNumber, String patientType, String name, String parentName, String visitType, String occupationName, String residentialAddress, String phoneNumber, String diagnosisName, String outpatientDoctorName, String sbp, String dbp, String t, String glu, String invoiceNumber, String stringinvoiceTime, String visitTime, String squareDepartmentName, String feeTotal, String feeCms, String feeCms_card, String feeYs, String feeRounding,String invalidSign) {
        this.sn = sn;
        this.organizationCode = organizationCode;
        this.visitNumber = visitNumber;
        this.idNumber = idNumber;
        this.age = age;
        this.archivesNumber = archivesNumber;
        this.birthdate = birthdate;
        this.cardNumber = cardNumber;
        this.patientType = patientType;
        this.name = name;
        this.parentName = parentName;
        this.visitType = visitType;
        this.occupationName = occupationName;
        this.residentialAddress = residentialAddress;
        this.phoneNumber = phoneNumber;
        this.diagnosisName = diagnosisName;
        this.outpatientDoctorName = outpatientDoctorName;
        this.sbp = sbp;
        this.dbp = dbp;
        this.t = t;
        this.glu = glu;
        this.invoiceNumber = invoiceNumber;
        StringinvoiceTime = stringinvoiceTime;
        this.visitTime = visitTime;
        this.squareDepartmentName = squareDepartmentName;
        this.feeTotal = feeTotal;
        this.feeCms = feeCms;
        this.feeCms_card = feeCms_card;
        this.feeYs = feeYs;
        this.feeRounding = feeRounding;
        this.invalidSign = invalidSign;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getArchivesNumber() {
        return archivesNumber;
    }

    public void setArchivesNumber(String archivesNumber) {
        this.archivesNumber = archivesNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getOutpatientDoctorName() {
        return outpatientDoctorName;
    }

    public void setOutpatientDoctorName(String outpatientDoctorName) {
        this.outpatientDoctorName = outpatientDoctorName;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getDbp() {
        return dbp;
    }

    public void setDbp(String dbp) {
        this.dbp = dbp;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getGlu() {
        return glu;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStringinvoiceTime() {
        return StringinvoiceTime;
    }

    public void setStringinvoiceTime(String stringinvoiceTime) {
        StringinvoiceTime = stringinvoiceTime;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getSquareDepartmentName() {
        return squareDepartmentName;
    }

    public void setSquareDepartmentName(String squareDepartmentName) {
        this.squareDepartmentName = squareDepartmentName;
    }

    public String getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(String feeTotal) {
        this.feeTotal = feeTotal;
    }

    public String getFeeCms() {
        return feeCms;
    }

    public void setFeeCms(String feeCms) {
        this.feeCms = feeCms;
    }

    public String getFeeCms_card() {
        return feeCms_card;
    }

    public void setFeeCms_card(String feeCms_card) {
        this.feeCms_card = feeCms_card;
    }

    public String getFeeYs() {
        return feeYs;
    }

    public void setFeeYs(String feeYs) {
        this.feeYs = feeYs;
    }

    public String getFeeRounding() {
        return feeRounding;
    }

    public void setFeeRounding(String feeRounding) {
        this.feeRounding = feeRounding;
    }

    public String getInvalidSign() {
        return invalidSign;
    }

    public void setInvalidSign(String invalidSign) {
        this.invalidSign = invalidSign;
    }
}
