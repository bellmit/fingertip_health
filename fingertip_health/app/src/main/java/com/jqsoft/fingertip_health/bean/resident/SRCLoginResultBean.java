package com.jqsoft.fingertip_health.bean.resident;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-08-16.
 * 签约居民端应用程序登录后返回的bean
 */

public class SRCLoginResultBean implements Serializable {
    private static final long serialVersionUID = 2421263553592651152L;


    private String personName;//                     用户名
    private String sexName;//                      性别
    private String cardNo;//                        身份证号
    private String phone;//                         签约人联系电话
    private String personID;//                      个人健康档案唯一标识符
    private String no;//                            健康档案号
    private String isHypertensionState;//              是否高血压患者
    private String isType2DiabetesState;//             是否糖尿病患者
    private String isPsychosisState;//                  是否精神病患者
    private String isOldPeopleInfoState;//             是否老年人
    private String isPoorState;//                      是否贫困人口
    private String isChildrenInfoState;
    private String isPregnantwomenInfoState;
    private String areaCode;//                       地区编码
    private String costName;//                       医保名称
    private String address;
    private String id;
    private String photoUrl;
    private String age;

    private List<FamilyMemberBean> familyMemberList;    //家庭成员列表


    //下面这两个变量不是服务端返回的----------------------
    public String loginSuccessUsername;
    public String loginSuccessPassword;
    //上面这两个变量不是服务端返回的----------------------

    public SRCLoginResultBean() {
        super();
    }

    public SRCLoginResultBean(String personName, String sexName, String cardNo, String phone, String personID, String no, String isHypertensionState, String isType2DiabetesState, String isPsychosisState, String isOldPeopleInfoState, String isPoorState, String isChildrenInfoState, String isPregnantwomenInfoState, String areaCode, String costName, String address, String id, String photoUrl, String age, List<FamilyMemberBean> familyMemberList, String loginSuccessUsername, String loginSuccessPassword) {
        this.personName = personName;
        this.sexName = sexName;
        this.cardNo = cardNo;
        this.phone = phone;
        this.personID = personID;
        this.no = no;
        this.isHypertensionState = isHypertensionState;
        this.isType2DiabetesState = isType2DiabetesState;
        this.isPsychosisState = isPsychosisState;
        this.isOldPeopleInfoState = isOldPeopleInfoState;
        this.isPoorState = isPoorState;
        this.isChildrenInfoState = isChildrenInfoState;
        this.isPregnantwomenInfoState = isPregnantwomenInfoState;
        this.areaCode = areaCode;
        this.costName = costName;
        this.address = address;
        this.id = id;
        this.photoUrl = photoUrl;
        this.age = age;
        this.familyMemberList = familyMemberList;
        this.loginSuccessUsername = loginSuccessUsername;
        this.loginSuccessPassword = loginSuccessPassword;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIsHypertensionState() {
        return isHypertensionState;
    }

    public void setIsHypertensionState(String isHypertensionState) {
        this.isHypertensionState = isHypertensionState;
    }

    public String getIsType2DiabetesState() {
        return isType2DiabetesState;
    }

    public void setIsType2DiabetesState(String isType2DiabetesState) {
        this.isType2DiabetesState = isType2DiabetesState;
    }

    public String getIsPsychosisState() {
        return isPsychosisState;
    }

    public void setIsPsychosisState(String isPsychosisState) {
        this.isPsychosisState = isPsychosisState;
    }

    public String getIsOldPeopleInfoState() {
        return isOldPeopleInfoState;
    }

    public void setIsOldPeopleInfoState(String isOldPeopleInfoState) {
        this.isOldPeopleInfoState = isOldPeopleInfoState;
    }

    public String getIsPoorState() {
        return isPoorState;
    }

    public void setIsPoorState(String isPoorState) {
        this.isPoorState = isPoorState;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FamilyMemberBean> getFamilyMemberList() {
        return familyMemberList;
    }

    public void setFamilyMemberList(List<FamilyMemberBean> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }

    public String getIsChildrenInfoState() {
        return isChildrenInfoState;
    }

    public void setIsChildrenInfoState(String isChildrenInfoState) {
        this.isChildrenInfoState = isChildrenInfoState;
    }

    public String getIsPregnantwomenInfoState() {
        return isPregnantwomenInfoState;
    }

    public void setIsPregnantwomenInfoState(String isPregnantwomenInfoState) {
        this.isPregnantwomenInfoState = isPregnantwomenInfoState;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLoginSuccessUsername() {
        return loginSuccessUsername;
    }

    public void setLoginSuccessUsername(String loginSuccessUsername) {
        this.loginSuccessUsername = loginSuccessUsername;
    }

    public String getLoginSuccessPassword() {
        return loginSuccessPassword;
    }

    public void setLoginSuccessPassword(String loginSuccessPassword) {
        this.loginSuccessPassword = loginSuccessPassword;
    }
}
