//package com.jqsoft.grassroots_civil_administration_platform.bean.response_new;
//
//import com.jqsoft.fingertip_health.bean.ServicePackDetailBeanList;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2017-07-10.
// */
////签约申请bean
//public class SignApplicationResultBean  {
//    private int position;//在列表中的位置
//
//
//
////    private String key;//   主键
////    private String personID;//个人表主键
////    private String cardNo;//身份证号码
////    private String no;//档案编号
////    private String personName;//姓名
////    private String sexName;//性别存储名称
////    private String phone;//电话号码（必须进行规则验证）
////    private String age;//申请时候年龄
////    private String personMold;//人员属性
////    private String applyTime;//申请时间（精确到秒）
////    private String serverPackageName;//申请服务包（多个服务包用顿号分割）
////    private String packID;//申请服务包主键（多个用顿号分割）
////    private String areaCode;//地区编码
////    private String address;//家庭地址
////    private String addTime;//录入时间
////    private String applyState;//申请状态（1： 申请中   2：取消申请  3：完成签约 ）
////    private String applyCancel;//取消申请原因
////    private String finishDoctor;//完成签约医生ID
////    private String finishTime;//完成签约时间
////    private String applyDoctor;//申请签约医生ID
////    private String applyDoctorName;//申请签约医生姓名
////    private String photoUrl;//个人照片
////    private String signPersonMold;//人员类型
//
//    public SignApplicationResultBean() {
//        super();
//    }
//
//    public SignApplicationResultBean(int index, String key, String fdSigningDoctorMode, String statusCode, String serverPackageName, String isPersonality, String signDT, String signMode, String signHomeCode, String signDeptName, String signDeptCode, String signDeptPhone, String teamName, String teamCode, String signTeamHeaderName, String signTeamHeaderCode, String signTeamHeaderPhone, String doctorName, String doctorCode, String doctorPhone, String userName, String sexCode, String cardNo, String guardianCardNo, String isUseGuardian, String phone, String agriculturalCardNo, String isHouseholder, String personID, String no, String isRelation, String filingStatue, String recordMode, String inputDeptCode, String inputDeptName, String areaCode, String areaName, String addUserCode, String addUserName, String addOrgId, String addDT, String updateUserCode, String updateUserName, String updateOrgId, String updateDT, String isFilingStatue, String personMold, String docOrganizationKey, String docOrganizationName, String docLoginName, String serviceContent, String isExecute, String docUserID, String isCharge, String actualPackageSumFee, String packSumFee, String newRuralCMSFee, String otherReduceFee, String shouldSelfFee, String jbggwsState, String signPageYear, String startExecData, String endExecData, String death, String familySysno, String memberSysno, String interfaceStatus, String basicPubilcMoney, String isPrintyjj, String idType, List<SignSeverPakesBeanList> detailInVoList) {
//
//
//    //    public SignApplicationResultBean(String key, String personID, String cardNo, String no, String personName, String sexName, String phone, String age, String personMold, String applyTime, String serverPackageName, String packID, String areaCode, String address, String addTime, String applyState, String applyCancel, String finishDoctor, String finishTime, String applyDoctor, String applyDoctorName, String photoUrl, String signPersonMold) {
////        this.key = key;
////        this.personID = personID;
////        this.cardNo = cardNo;
////        this.no = no;
////        this.personName = personName;
////        this.sexName = sexName;
////        this.phone = phone;
////        this.age = age;
////        this.personMold = personMold;
////        this.applyTime = applyTime;
////        this.serverPackageName = serverPackageName;
////        this.packID = packID;
////        this.areaCode = areaCode;
////        this.address = address;
////        this.addTime = addTime;
////        this.applyState = applyState;
////        this.applyCancel = applyCancel;
////        this.finishDoctor = finishDoctor;
////        this.finishTime = finishTime;
////        this.applyDoctor = applyDoctor;
////        this.applyDoctorName = applyDoctorName;
////        this.photoUrl = photoUrl;
////        this.signPersonMold = signPersonMold;
////    }
////
//
//
//
//    public void setPosition(int position) {
//        this.position = position;
//    }
//
//
////    public String getKey() {
////        return key;
////    }
////
////    public void setKey(String key) {
////        this.key = key;
////    }
////
////    public String getPersonID() {
////        return personID;
////    }
////
////    public void setPersonID(String personID) {
////        this.personID = personID;
////    }
////
////    public String getCardNo() {
////        return cardNo;
////    }
////
////    public void setCardNo(String cardNo) {
////        this.cardNo = cardNo;
////    }
////
////    public String getNo() {
////        return no;
////    }
////
////    public void setNo(String no) {
////        this.no = no;
////    }
////
////    public String getPersonName() {
////        return personName;
////    }
////
////    public void setPersonName(String personName) {
////        this.personName = personName;
////    }
////
////    public String getSexName() {
////        return sexName;
////    }
////
////    public void setSexName(String sexName) {
////        this.sexName = sexName;
////    }
////
////    public String getPhone() {
////        return phone;
////    }
////
////    public void setPhone(String phone) {
////        this.phone = phone;
////    }
////
////    public String getAge() {
////        return age;
////    }
////
////    public void setAge(String age) {
////        this.age = age;
////    }
////
////    public String getPersonMold() {
////        return personMold;
////    }
////
////    public void setPersonMold(String personMold) {
////        this.personMold = personMold;
////    }
////
////    public String getApplyTime() {
////        return applyTime;
////    }
////
////    public void setApplyTime(String applyTime) {
////        this.applyTime = applyTime;
////    }
////
////    public String getServerPackageName() {
////        return serverPackageName;
////    }
////
////    public void setServerPackageName(String serverPackageName) {
////        this.serverPackageName = serverPackageName;
////    }
////
////    public String getPackID() {
////        return packID;
////    }
////
////    public void setPackID(String packID) {
////        this.packID = packID;
////    }
////
////    public String getAreaCode() {
////        return areaCode;
////    }
////
////    public void setAreaCode(String areaCode) {
////        this.areaCode = areaCode;
////    }
////
////    public String getAddress() {
////        return address;
////    }
////
////    public void setAddress(String address) {
////        this.address = address;
////    }
////
////    public String getAddTime() {
////        return addTime;
////    }
////
////    public void setAddTime(String addTime) {
////        this.addTime = addTime;
////    }
////
////    public String getApplyState() {
////        return applyState;
////    }
////
////    public void setApplyState(String applyState) {
////        this.applyState = applyState;
////    }
////
////    public String getApplyCancel() {
////        return applyCancel;
////    }
////
////    public void setApplyCancel(String applyCancel) {
////        this.applyCancel = applyCancel;
////    }
////
////    public String getFinishDoctor() {
////        return finishDoctor;
////    }
////
////    public void setFinishDoctor(String finishDoctor) {
////        this.finishDoctor = finishDoctor;
////    }
////
////    public String getFinishTime() {
////        return finishTime;
////    }
////
////    public void setFinishTime(String finishTime) {
////        this.finishTime = finishTime;
////    }
////
////    public String getApplyDoctor() {
////        return applyDoctor;
////    }
////
////    public void setApplyDoctor(String applyDoctor) {
////        this.applyDoctor = applyDoctor;
////    }
////
////    public String getApplyDoctorName() {
////        return applyDoctorName;
////    }
////
////    public void setApplyDoctorName(String applyDoctorName) {
////        this.applyDoctorName = applyDoctorName;
////    }
////
////    public String getPhotoUrl() {
////        return photoUrl;
////    }
////
////    public void setPhotoUrl(String photoUrl) {
////        this.photoUrl = photoUrl;
////    }
////
////    public String getSignPersonMold() {
////        return signPersonMold;
////    }
////
////    public void setSignPersonMold(String signPersonMold) {
////        this.signPersonMold = signPersonMold;
////    }
//}
