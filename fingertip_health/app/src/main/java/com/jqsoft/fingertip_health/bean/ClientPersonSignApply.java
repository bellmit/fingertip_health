package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/8/22.
 */

public class ClientPersonSignApply {
    private String photoUrl;//                    个人照片
    private String personName;//               姓名
    private String sexName;//                性别存储名称
    private String age;//                申请时候年龄
    private String personMold;//                人员属性
    private String cardNo;//            身份证号码
    private String no;// ;//        档案编号
    private String applyTime;// 申请时间（精确到秒）
    private String serverPackageName;//        申请服 务包（多个服务包用空格分割）
    private String packID;//     申请服务包主键（多个用空格分割）
    private String address;//   家庭地址
    private String applyKey;//           主键
    private String personID;//      ;//  个人表主键
    private String phone;//  电话号码（必须进行规则验证）
    private String applyDoctor;//        申请签约医生ID
    private String applyDoctorName;//   申请签约医生姓名
    private String actualPackageSumFee;//实收金额总计
    private String packSumFee;//费用总计
    private String newRuralCMSFee;//新农合补偿金额总计
    private String otherReduceFee;//减免金额总计
    private String shouldSelfFee;//应自付金额总计
    private String orgName;
    public ClientPersonSignApply() {
    }

    public ClientPersonSignApply(String photoUrl, String personName, String sexName, String age, String personMold, String cardNo, String no, String applyTime, String serverPackageName, String packID, String address, String applyKey, String personID, String phone, String applyDoctor, String applyDoctorName, String actualPackageSumFee, String packSumFee, String newRuralCMSFee, String otherReduceFee, String shouldSelfFee,String orgName) {
        this.photoUrl = photoUrl;
        this.personName = personName;
        this.sexName = sexName;
        this.age = age;
        this.personMold = personMold;
        this.cardNo = cardNo;
        this.no = no;
        this.applyTime = applyTime;
        this.serverPackageName = serverPackageName;
        this.packID = packID;
        this.address = address;
        this.applyKey = applyKey;
        this.personID = personID;
        this.phone = phone;
        this.applyDoctor = applyDoctor;
        this.applyDoctorName = applyDoctorName;
        this.actualPackageSumFee = actualPackageSumFee;
        this.packSumFee = packSumFee;
        this.newRuralCMSFee = newRuralCMSFee;
        this.otherReduceFee = otherReduceFee;
        this.shouldSelfFee = shouldSelfFee;
        this.orgName =orgName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPersonMold() {
        return personMold;
    }

    public void setPersonMold(String personMold) {
        this.personMold = personMold;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getServerPackageName() {
        return serverPackageName;
    }

    public void setServerPackageName(String serverPackageName) {
        this.serverPackageName = serverPackageName;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplyKey() {
        return applyKey;
    }

    public void setApplyKey(String applyKey) {
        this.applyKey = applyKey;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApplyDoctor() {
        return applyDoctor;
    }

    public void setApplyDoctor(String applyDoctor) {
        this.applyDoctor = applyDoctor;
    }

    public String getApplyDoctorName() {
        return applyDoctorName;
    }

    public void setApplyDoctorName(String applyDoctorName) {
        this.applyDoctorName = applyDoctorName;
    }
    public String getActualPackageSumFee() {
        return actualPackageSumFee;
    }

    public void setActualPackageSumFee(String actualPackageSumFee) {
        this.actualPackageSumFee = actualPackageSumFee;
    }

    public String getPackSumFee() {
        return packSumFee;
    }

    public void setPackSumFee(String packSumFee) {
        this.packSumFee = packSumFee;
    }

    public String getNewRuralCMSFee() {
        return newRuralCMSFee;
    }

    public void setNewRuralCMSFee(String newRuralCMSFee) {
        this.newRuralCMSFee = newRuralCMSFee;
    }

    public String getOtherReduceFee() {
        return otherReduceFee;
    }

    public void setOtherReduceFee(String otherReduceFee) {
        this.otherReduceFee = otherReduceFee;
    }

    public String getShouldSelfFee() {
        return shouldSelfFee;
    }

    public void setShouldSelfFee(String shouldSelfFee) {
        this.shouldSelfFee = shouldSelfFee;
    }
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
