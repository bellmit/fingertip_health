package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/7/11.
 */

public class PatientBean {
    public static String username;
    public static String sexCode;
    public static String cardNo;
    public static String guardianCardNo;
    public static String isUseGuardian;
    public static String phone;
    public static String agriculturalCardNo;
    public static String isHouseholder;//               是否是户主
    public static String personID;//            个人健康档案唯一标识符
    public static String interfaceStatus;//            1 代表关联农合成功
    public static String no;
    public static String familySysno;
    public static String memberNO;

    public static String getYibao_code() {
        return yibao_code;
    }

    public static void setYibao_code(String yibao_code) {
        PatientBean.yibao_code = yibao_code;
    }

    public static  String  yibao_code ;

    public static String getFamilySysno() {
        return familySysno;
    }

    public static void setFamilySysno(String familySysno) {
        PatientBean.familySysno = familySysno;
    }

    public static String getMemberNO() {
        return memberNO;
    }

    public static void setMemberNO(String memberNO) {
        PatientBean.memberNO = memberNO;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        PatientBean.username = username;
    }

    public static String getSexCode() {
        return sexCode;
    }

    public static void setSexCode(String sexCode) {
        PatientBean.sexCode = sexCode;
    }

    public static String getCardNo() {
        return cardNo;
    }

    public static void setCardNo(String cardNo) {
        PatientBean.cardNo = cardNo;
    }

    public static String getGuardianCardNo() {
        return guardianCardNo;
    }

    public static void setGuardianCardNo(String guardianCardNo) {
        PatientBean.guardianCardNo = guardianCardNo;
    }

    public static String getIsUseGuardian() {
        return isUseGuardian;
    }

    public static void setIsUseGuardian(String isUseGuardian) {
        PatientBean.isUseGuardian = isUseGuardian;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        PatientBean.phone = phone;
    }

    public static String getAgriculturalCardNo() {
        return agriculturalCardNo;
    }

    public static void setAgriculturalCardNo(String agriculturalCardNo) {
        PatientBean.agriculturalCardNo = agriculturalCardNo;
    }

    public static String getIsHouseholder() {
        return isHouseholder;
    }

    public static void setIsHouseholder(String isHouseholder) {
        PatientBean.isHouseholder = isHouseholder;
    }

    public static String getPersonID() {
        return personID;
    }

    public static void setPersonID(String personID) {
        PatientBean.personID = personID;
    }

    public static String getInterfaceStatus() {
        return interfaceStatus;
    }

    public static void setInterfaceStatus(String interfaceStatus) {
        PatientBean.interfaceStatus = interfaceStatus;
    }

    public static String getNo() {
        return no;
    }

    public static void setNo(String no) {
        PatientBean.no = no;
    }
}
