package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/7/10.
 */

public class SignTeamBean {
    public  String signDeptCode;//签约机构编码
    public  String signDeptName;//签约机构名称
    public  String signDeptPhone;//签约机构联系方式
    public  String teamCode;//签约团队编码
    public  String teamName;//签约团队名称
    public  String signTeamHeaderCode;//签约团队负责人编码
    public  String signTeamHeaderName;//签约团队负责人名称
    public  String signTeamHeaderPhone;//签约团队负责人联系方式
    public  String doctorName;//签约医生名称
    public  String doctorCode;//签约医生编码
    public  String doctorPhone;//签约医生联系方式
    public  String docUserId;//签约医生主键
    public  String guidedoctorsCode;//指导医生编码
    public  String guidedoctorsName;//指导医生名称

    public SignTeamBean() {
    }

    public SignTeamBean(String signDeptCode, String signDeptName, String signDeptPhone, String teamCode, String teamName, String signTeamHeaderCode, String signTeamHeaderName, String signTeamHeaderPhone, String doctorName, String doctorCode, String doctorPhone, String docUserId, String guidedoctorsCode, String guidedoctorsName) {
        this.signDeptCode = signDeptCode;
        this.signDeptName = signDeptName;
        this.signDeptPhone = signDeptPhone;
        this.teamCode = teamCode;
        this.teamName = teamName;
        this.signTeamHeaderCode = signTeamHeaderCode;
        this.signTeamHeaderName = signTeamHeaderName;
        this.signTeamHeaderPhone = signTeamHeaderPhone;
        this.doctorName = doctorName;
        this.doctorCode = doctorCode;
        this.doctorPhone = doctorPhone;
        this.docUserId = docUserId;
        this.guidedoctorsCode = guidedoctorsCode;
        this.guidedoctorsName = guidedoctorsName;
    }

    public String getSignDeptCode() {
        return signDeptCode;
    }

    public void setSignDeptCode(String signDeptCode) {
        this.signDeptCode = signDeptCode;
    }

    public String getSignDeptName() {
        return signDeptName;
    }

    public void setSignDeptName(String signDeptName) {
        this.signDeptName = signDeptName;
    }

    public String getSignDeptPhone() {
        return signDeptPhone;
    }

    public void setSignDeptPhone(String signDeptPhone) {
        this.signDeptPhone = signDeptPhone;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSignTeamHeaderCode() {
        return signTeamHeaderCode;
    }

    public void setSignTeamHeaderCode(String signTeamHeaderCode) {
        this.signTeamHeaderCode = signTeamHeaderCode;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDocUserId() {
        return docUserId;
    }

    public void setDocUserId(String docUserId) {
        this.docUserId = docUserId;
    }

    public String getGuidedoctorsCode() {
        return guidedoctorsCode;
    }

    public void setGuidedoctorsCode(String guidedoctorsCode) {
        this.guidedoctorsCode = guidedoctorsCode;
    }

    public String getGuidedoctorsName() {
        return guidedoctorsName;
    }

    public void setGuidedoctorsName(String guidedoctorsName) {
        this.guidedoctorsName = guidedoctorsName;
    }


}
