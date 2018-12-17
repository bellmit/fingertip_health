package com.jqsoft.fingertip_health.bean.fingertip;

import java.util.List;

/**
 * Created by Administrator on 2018-09-04.
 */

public class LoginResultForFingertip {
    private int flagdangan=0;

    private String device;//设备代码
    private String token;//会话密钥

    private String username;//	用户登录名
    private String office;//	所属科室代码
    private String orgCode;//	机构代码
    private String areaId;//	用户地区代码
    private String orgName;//	机构名称
    private String userLevel;//	用户级别
    private String realName;//	真实姓名
    private List<DoctorBeanForFingertip> doctor;//	可选医生列表

    private String cmsIcdArea;//
    private String cmsType;//
    private String versionNum;//

    private String phone;


    public LoginResultForFingertip() {
        super();
    }

    public LoginResultForFingertip(String device, String token, String username, String office, String orgCode, String areaId, String orgName, String userLevel, String realName, List<DoctorBeanForFingertip> doctor) {
        this.device = device;
        this.token = token;
        this.username = username;
        this.office = office;
        this.orgCode = orgCode;
        this.areaId = areaId;
        this.orgName = orgName;
        this.userLevel = userLevel;
        this.realName = realName;
        this.doctor = doctor;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<DoctorBeanForFingertip> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<DoctorBeanForFingertip> doctor) {
        this.doctor = doctor;
    }

    public String getCmsIcdArea() {
        return cmsIcdArea;
    }

    public void setCmsIcdArea(String cmsIcdArea) {
        this.cmsIcdArea = cmsIcdArea;
    }

    public String getCmsType() {
        return cmsType;
    }

    public void setCmsType(String cmsType) {
        this.cmsType = cmsType;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFlagdangan() {
        return flagdangan;
    }

    public void setFlagdangan(int flagdangan) {
        this.flagdangan = flagdangan;
    }
}
