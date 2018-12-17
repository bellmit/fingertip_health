package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Mars on 2018/1/30.
 */

public class PersonalInfoBean {
    private String areaId;
    private String cardNo;
    private String id;
    private String mobiePhone;
    private String passWord;
    private String realName;
    private String userName;
    private String createTime;
    private String rcType;
    private String role;
    private String roleId;
    private String unit;
    private String area;

    public PersonalInfoBean(String areaId, String cardNo, String id, String mobiePhone, String passWord, String realName, String userName, String createTime, String rcType, String role, String roleId, String unit, String area) {
        this.areaId = areaId;
        this.cardNo = cardNo;
        this.id = id;
        this.mobiePhone = mobiePhone;
        this.passWord = passWord;
        this.realName = realName;
        this.userName = userName;
        this.createTime = createTime;
        this.rcType = rcType;
        this.role = role;
        this.roleId = roleId;
        this.unit = unit;
        this.area = area;
    }

    public PersonalInfoBean() {
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobiePhone() {
        return mobiePhone;
    }

    public void setMobiePhone(String mobiePhone) {
        this.mobiePhone = mobiePhone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRcType() {
        return rcType;
    }

    public void setRcType(String rcType) {
        this.rcType = rcType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
