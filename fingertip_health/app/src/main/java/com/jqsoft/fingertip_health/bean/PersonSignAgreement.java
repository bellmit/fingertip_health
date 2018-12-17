package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/8/30.
 */

public class PersonSignAgreement {
    private String personName;//                居民名称
    private String personPhone;//     居民手机号码
    private String areaTownName;//   地区乡镇
    private String areaVillageName;//      村
    private String areaGroup;//      组
    private String serverPackageName;//     服务包
    private String shouldSelfFee;//      自费
    private String doctorName;//    家庭医生
    private String doctorPhone;//      家庭医生手机号码
    private String signTeamHeaderName;//    团队负责人
    private String signTeamHeaderPhone;//      团队负责人手机号码
    private String signDeptName;//      管理指导单位
    private String docOrganizationName;//       签约机构
    private String year;//      签约年份
    private String startExec;//开始时间
    private String endExec;//结束时间



    public PersonSignAgreement() {
    }

    public PersonSignAgreement(String personName, String personPhone, String areaTownName, String areaVillageName, String areaGroup, String serverPackageName, String shouldSelfFee, String doctorName, String doctorPhone, String signTeamHeaderName, String signTeamHeaderPhone, String signDeptName, String docOrganizationName, String year,String startExec,String endExec) {
        this.personName = personName;
        this.personPhone = personPhone;
        this.areaTownName = areaTownName;
        this.areaVillageName = areaVillageName;
        this.areaGroup = areaGroup;
        this.serverPackageName = serverPackageName;
        this.shouldSelfFee = shouldSelfFee;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.signTeamHeaderName = signTeamHeaderName;
        this.signTeamHeaderPhone = signTeamHeaderPhone;
        this.signDeptName = signDeptName;
        this.docOrganizationName = docOrganizationName;
        this.year = year;
        this.startExec = startExec;
        this.endExec =endExec;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getAreaTownName() {
        return areaTownName;
    }

    public void setAreaTownName(String areaTownName) {
        this.areaTownName = areaTownName;
    }

    public String getAreaVillageName() {
        return areaVillageName;
    }

    public void setAreaVillageName(String areaVillageName) {
        this.areaVillageName = areaVillageName;
    }

    public String getAreaGroup() {
        return areaGroup;
    }

    public void setAreaGroup(String areaGroup) {
        this.areaGroup = areaGroup;
    }

    public String getServerPackageName() {
        return serverPackageName;
    }

    public void setServerPackageName(String serverPackageName) {
        this.serverPackageName = serverPackageName;
    }

    public String getShouldSelfFee() {
        return shouldSelfFee;
    }

    public void setShouldSelfFee(String shouldSelfFee) {
        this.shouldSelfFee = shouldSelfFee;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getSignTeamHeaderName() {
        return signTeamHeaderName;
    }

    public void setSignTeamHeaderName(String signTeamHeaderName) {
        this.signTeamHeaderName = signTeamHeaderName;
    }

    public String getSignTeamHeaderPhone() {
        return signTeamHeaderPhone;
    }

    public void setSignTeamHeaderPhone(String signTeamHeaderPhone) {
        this.signTeamHeaderPhone = signTeamHeaderPhone;
    }

    public String getSignDeptName() {
        return signDeptName;
    }

    public void setSignDeptName(String signDeptName) {
        this.signDeptName = signDeptName;
    }

    public String getDocOrganizationName() {
        return docOrganizationName;
    }

    public void setDocOrganizationName(String docOrganizationName) {
        this.docOrganizationName = docOrganizationName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStartExec() {
        return startExec;
    }

    public void setStartExec(String startExec) {
        this.startExec = startExec;
    }

    public String getEndExec() {
        return endExec;
    }

    public void setEndExec(String endExec) {
        this.endExec = endExec;
    }
}
