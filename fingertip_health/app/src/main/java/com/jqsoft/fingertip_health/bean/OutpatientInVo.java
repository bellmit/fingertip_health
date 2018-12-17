package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/9/10.
 */

public class OutpatientInVo {
    private String visitNumber;//就诊号	String		为空自动生成
    private String patientId;//病人ID	String	N/Y	农合病人必传（读卡返回），自费自动生成
    private String patientType;//病人类型	String	N	0自费，3农合
    private String archivesNumber;//	公共卫生档案号	String
    private String name;//患者姓名	String	N
    private String sex;//	患者性别名称	String		男/女
    private String birthdate;//出生日期	String		yyyy-MM-dd
    private String idNumber;//	身份证号	String
    private String age;//年龄	String		栗子：25岁 、12天 、7月
    private String maritalStatus;//	婚姻状况	String		见字典MARITAL_STATUS
    private String phoneNumber;//联系电话	String
    private String signDoctor;//签约医生	String
    private String residentialAddress;//居住地址	String
    private String cardNumber;//	医疗证号		N/Y	农合病人必传
    private String outpatientDoctorCode;//	开方医生编码	String	N
    private String outpatientDoctorName;//	开方医生名称	String	N
    private String cmsMemberPro;//农合人员属性名称	String		农合读卡返回
    private String familySysno;//农合家庭编号	String		农合读卡返回
    private String occupationName;//	职业	String
    private String parentName;//家长姓名	String

    public OutpatientInVo() {
    }

    public OutpatientInVo(String visitNumber, String patientId, String patientType, String archivesNumber, String name, String sex, String birthdate, String idNumber, String age, String maritalStatus, String phoneNumber, String signDoctor, String residentialAddress, String cardNumber, String outpatientDoctorCode, String outpatientDoctorName, String cmsMemberPro, String familySysno, String occupationName, String parentName) {
        this.visitNumber = visitNumber;
        this.patientId = patientId;
        this.patientType = patientType;
        this.archivesNumber = archivesNumber;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.idNumber = idNumber;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.phoneNumber = phoneNumber;
        this.signDoctor = signDoctor;
        this.residentialAddress = residentialAddress;
        this.cardNumber = cardNumber;
        this.outpatientDoctorCode = outpatientDoctorCode;
        this.outpatientDoctorName = outpatientDoctorName;
        this.cmsMemberPro = cmsMemberPro;
        this.familySysno = familySysno;
        this.occupationName = occupationName;
        this.parentName = parentName;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getArchivesNumber() {
        return archivesNumber;
    }

    public void setArchivesNumber(String archivesNumber) {
        this.archivesNumber = archivesNumber;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSignDoctor() {
        return signDoctor;
    }

    public void setSignDoctor(String signDoctor) {
        this.signDoctor = signDoctor;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOutpatientDoctorCode() {
        return outpatientDoctorCode;
    }

    public void setOutpatientDoctorCode(String outpatientDoctorCode) {
        this.outpatientDoctorCode = outpatientDoctorCode;
    }

    public String getOutpatientDoctorName() {
        return outpatientDoctorName;
    }

    public void setOutpatientDoctorName(String outpatientDoctorName) {
        this.outpatientDoctorName = outpatientDoctorName;
    }

    public String getCmsMemberPro() {
        return cmsMemberPro;
    }

    public void setCmsMemberPro(String cmsMemberPro) {
        this.cmsMemberPro = cmsMemberPro;
    }

    public String getFamilySysno() {
        return familySysno;
    }

    public void setFamilySysno(String familySysno) {
        this.familySysno = familySysno;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
