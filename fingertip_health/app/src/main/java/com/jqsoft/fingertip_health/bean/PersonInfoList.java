package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/7/12.
 */

public class PersonInfoList {
    private String memberNO;//		        成员编码
    private String name;//	            姓名
    private String countryTeamCode;//	      辖区编码
    private String familySysno;//		        家庭编码
    private String sexId;//	            性别代码 （ 1：男性，2：女性）
    private String idcardNo;//	           身份证
    private String Age;//	            年龄
    private String birthday;//	            出生日期
    private String bookNo;//	           医疗证号
    private String cardNo;//	            医疗卡号
    private String familyAddress;//	         家庭住址
    private String Tel;//	        电话号码
    private String ideName;//	      个人身份属性名称
    public boolean ischecked;

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public PersonInfoList(String memberNO, String name, String countryTeamCode, String familySysno, String sexId, String idcardNo, String age, String birthday, String bookNo, String cardNo, String familyAddress, String tel, String ideName) {
        this.memberNO = memberNO;
        this.name = name;
        this.countryTeamCode = countryTeamCode;
        this.familySysno = familySysno;
        this.sexId = sexId;
        this.idcardNo = idcardNo;
        this.Age = age;
        this.birthday = birthday;
        this.bookNo = bookNo;
        this.cardNo = cardNo;
        this.familyAddress = familyAddress;
        this.Tel = tel;
        this.ideName = ideName;
    }

    public PersonInfoList() {
    }

    public PersonInfoList(String memberNO) {
        this.memberNO = memberNO;
    }

    public String getMemberNO() {
        return memberNO;
    }

    public void setMemberNO(String memberNO) {
        this.memberNO = memberNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryTeamCode() {
        return countryTeamCode;
    }

    public void setCountryTeamCode(String countryTeamCode) {
        this.countryTeamCode = countryTeamCode;
    }

    public String getFamilySysno() {
        return familySysno;
    }

    public void setFamilySysno(String familySysno) {
        this.familySysno = familySysno;
    }

    public String getSexId() {
        return sexId;
    }

    public void setSexId(String sexId) {
        this.sexId = sexId;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getIdeName() {
        return ideName;
    }

    public void setIdeName(String ideName) {
        this.ideName = ideName;
    }
}
