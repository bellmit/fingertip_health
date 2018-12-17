package com.jqsoft.fingertip_health.bean.fingertip;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-09-07.
 */

public class OutpatientBeanForFingertip implements Serializable{
    private String birthday;//	出生日期
    private String medicalNo;//	医疗证号
    private String memberId;//	成员ID
    private String year;//	年份
    private String memberAge;//	年龄
    private String idCard;//	身份证号
    private String outpCompensateCost;//	个人累计补偿
    private String memberName;//	姓名
    private String areaName;//	地址
    private String memberSex;//	性别
    private String familyId;//	家庭编号
    private String memberPro;//	人员属性
    private String relation;

    public OutpatientBeanForFingertip() {
        super();
    }

    public OutpatientBeanForFingertip(String relation ,String birthday, String medicalNo, String memberId, String year, String memberAge, String idCard, String outpCompensateCost, String memberName, String areaName, String memberSex, String familyId, String memberPro) {
       this.relation =relation;
        this.birthday = birthday;
        this.medicalNo = medicalNo;
        this.memberId = memberId;
        this.year = year;
        this.memberAge = memberAge;
        this.idCard = idCard;
        this.outpCompensateCost = outpCompensateCost;
        this.memberName = memberName;
        this.areaName = areaName;
        this.memberSex = memberSex;
        this.familyId = familyId;
        this.memberPro = memberPro;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(String medicalNo) {
        this.medicalNo = medicalNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(String memberAge) {
        this.memberAge = memberAge;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOutpCompensateCost() {
        return outpCompensateCost;
    }

    public void setOutpCompensateCost(String outpCompensateCost) {
        this.outpCompensateCost = outpCompensateCost;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(String memberSex) {
        this.memberSex = memberSex;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getMemberPro() {
        return memberPro;
    }

    public void setMemberPro(String memberPro) {
        this.memberPro = memberPro;
    }
}
