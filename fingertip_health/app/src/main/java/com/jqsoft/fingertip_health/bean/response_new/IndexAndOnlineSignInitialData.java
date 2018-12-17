package com.jqsoft.fingertip_health.bean.response_new;

import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;

import java.util.List;

/**
 * Created by Administrator on 2017-07-20.
 */
//在线签约初始化数据(从签约申请列表中每一项获取)
public class IndexAndOnlineSignInitialData {
    private int index;//在线签约索引
    private int position;
    private String applyKey;//主键
    private String personID;//个人表主键
    private String cardNo;//身份证号码
    private String no;//档案编号
    private String personName;//姓名
    private String sexName;//性别存储名称
    private String Phone;//电话号码（必须进行规则验证）
    private String Age;//申请时候年龄
    private String personMold;//人员属性
    private String applyTime;//申请时间（精确到秒）
    private String serverPackageName;//申请服务包（多个服务包用顿号分割）
    private String packID;//申请服务包主键（多个用顿号分割）
    private String Address;//家庭地址
    private String addTime;//录入时间
    private String applyDoctor;//申请签约医生ID
    private String applyDoctorName;//申请签约医生姓名
    private String photoUrl;//个人照片
    private String signEdit;//(0:在线签约  1 :签约申请)
    private String signHomeCode;//户编号（一户生成一个GUID）
    private String isPersonality;//是否含个性化服务（1是、0否）
    private String actualPackageSumFee;//实收金额总计
    private String packSumFee;//费用总计
    private String newRuralCMSFee;//新农合补偿金额总计
    private String otherReduceFee;//减免金额总计
    private String shouldSelfFee;//应自付金额总计
    private String signPageYear;//签约服务包年份
    private String sexCode;//签约人性别编号
    private String guardianCardNo;//监护人身份证号
    private String isUseGuardian;//是否启用监护人
    private String agriculturalCardNo;//医保类型
            //(0 新农合  1职工医保 3居民医保 4其他)
    private String isHouseholder;//是否是户主
    private String recordMode;//录入方式
    //（1手动、2农合卡、3医保卡、4身份证）
    private String areaName;//地区名称
    private String areaCode;//地区编码
    private String familySysno;//家庭编码
    private String memberSysno;//人员编码
    private String interfaceStatus;//新农合关联状态
    //（1：代表关联农合成功，2：代表未关联农合成功）


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private List<SignSeverPakesBeanList> signDoctorList;//                     签约服务包明细 list：

    public IndexAndOnlineSignInitialData() {
    }

    public IndexAndOnlineSignInitialData(int index, String applyKey, String personID, String cardNo, String no, String personName, String sexName, String phone, String age, String personMold, String applyTime, String serverPackageName, String packID, String address, String addTime, String applyDoctor, String applyDoctorName, String photoUrl, String signEdit, String signHomeCode, String isPersonality, String actualPackageSumFee, String packSumFee, String newRuralCMSFee, String otherReduceFee, String shouldSelfFee, String signPageYear, String sexCode, String guardianCardNo, String isUseGuardian, String agriculturalCardNo, String isHouseholder, String recordMode, String areaName, String areaCode, String familySysno, String memberSysno, String interfaceStatus, List<SignSeverPakesBeanList> signDoctorList, int position) {
        this.index = index;
        this.applyKey = applyKey;
        this.personID = personID;
        this.cardNo = cardNo;
        this.no = no;
        this.personName = personName;
        this.sexName = sexName;
        Phone = phone;
        Age = age;
        this.personMold = personMold;
        this.applyTime = applyTime;
        this.serverPackageName = serverPackageName;
        this.packID = packID;
        Address = address;
        this.addTime = addTime;
        this.applyDoctor = applyDoctor;
        this.applyDoctorName = applyDoctorName;
        this.photoUrl = photoUrl;
        this.signEdit = signEdit;
        this.signHomeCode = signHomeCode;
        this.isPersonality = isPersonality;
        this.actualPackageSumFee = actualPackageSumFee;
        this.packSumFee = packSumFee;
        this.newRuralCMSFee = newRuralCMSFee;
        this.otherReduceFee = otherReduceFee;
        this.shouldSelfFee = shouldSelfFee;
        this.signPageYear = signPageYear;
        this.sexCode = sexCode;
        this.guardianCardNo = guardianCardNo;
        this.isUseGuardian = isUseGuardian;
        this.agriculturalCardNo = agriculturalCardNo;
        this.isHouseholder = isHouseholder;
        this.recordMode = recordMode;
        this.areaName = areaName;
        this.areaCode = areaCode;
        this.familySysno = familySysno;
        this.memberSysno = memberSysno;
        this.interfaceStatus = interfaceStatus;
        this.signDoctorList = signDoctorList;
        this.position=position;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPersonMold() {
        return personMold;
    }

    public void setPersonMold(String personMold) {
        this.personMold = personMold;
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
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSignEdit() {
        return signEdit;
    }

    public void setSignEdit(String signEdit) {
        this.signEdit = signEdit;
    }

    public String getSignHomeCode() {
        return signHomeCode;
    }

    public void setSignHomeCode(String signHomeCode) {
        this.signHomeCode = signHomeCode;
    }

    public String getIsPersonality() {
        return isPersonality;
    }

    public void setIsPersonality(String isPersonality) {
        this.isPersonality = isPersonality;
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

    public String getSignPageYear() {
        return signPageYear;
    }

    public void setSignPageYear(String signPageYear) {
        this.signPageYear = signPageYear;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getGuardianCardNo() {
        return guardianCardNo;
    }

    public void setGuardianCardNo(String guardianCardNo) {
        this.guardianCardNo = guardianCardNo;
    }

    public String getIsUseGuardian() {
        return isUseGuardian;
    }

    public void setIsUseGuardian(String isUseGuardian) {
        this.isUseGuardian = isUseGuardian;
    }

    public String getAgriculturalCardNo() {
        return agriculturalCardNo;
    }

    public void setAgriculturalCardNo(String agriculturalCardNo) {
        this.agriculturalCardNo = agriculturalCardNo;
    }

    public String getIsHouseholder() {
        return isHouseholder;
    }

    public void setIsHouseholder(String isHouseholder) {
        this.isHouseholder = isHouseholder;
    }

    public String getRecordMode() {
        return recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getFamilySysno() {
        return familySysno;
    }

    public void setFamilySysno(String familySysno) {
        this.familySysno = familySysno;
    }

    public String getMemberSysno() {
        return memberSysno;
    }

    public void setMemberSysno(String memberSysno) {
        this.memberSysno = memberSysno;
    }

    public String getInterfaceStatus() {
        return interfaceStatus;
    }

    public void setInterfaceStatus(String interfaceStatus) {
        this.interfaceStatus = interfaceStatus;
    }

    public List<SignSeverPakesBeanList> getSignDoctorList() {
        return signDoctorList;
    }

    public void setSignDoctorList(List<SignSeverPakesBeanList> signDoctorList) {
        this.signDoctorList = signDoctorList;
    }
    //
//    private String key;//	String	N 主键(xx-xx-xx-xx-xx) UUID  格式为(D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
//    private String fdSigningDoctorMode;//	String	N	（签约方式： 1：按年度签约  2：随到随签）
//    private String statusCode;//	String	N	1：起草中、2：已提交（已签约）、3：审核通过、4：审核不通过，5解约
//    private String serverPackageName;//	String	N	服务包名称，多个用分号隔开
//    private String isPersonality;//	String	N	是否含个性化服务（1是、0否）
//    private String signDT;//	String	N	签约时间
//    private String signMode;//	String	N	签约形式（1、家庭、2、个人）
//    private String signHomeCode;//	String	N	户编号（一户生成一个GUID）
//    private String signDeptName;//	String	N	签约机构名称(甲方)
//    private String signDeptCode;//	String	N	签约机构编码
//    private String signDeptPhone;//	String	Y 机构的联系电话
//    private String teamName;//	String	N	团队名称
//    private String teamCode;//	String	N	团队编码
//    private String signTeamHeaderName;//	String	N	签约团队负责人姓名
//    private String signTeamHeaderCode;//	String	N	签约团队负责人编码
//    private String signTeamHeaderPhone;//	String	y	签约团队负责人电话
//    private String doctorName;//	String	N	家庭医生姓名
//    private String doctorCode;//	String	N	家庭医生编码
//    private String doctorPhone;//	String	y	家庭医生电话
//    private String userName;//	String	N	签约人姓名
//    private String sexCode;//	String	N	签约人性别编号
//    private String cardNo;//	String	y	签约人身份证号(isUseGuardian = 0  必填)
//    private String guardianCardNo;//	String	y	监护人身份证号(isUseGuardian = 1  必填)
//    private String isUseGuardian;//	String	N	是否启用监护人 0 代表本人身份证号码，1代表的是 监护人身份证号码
//    private String phone;//	String	N	签约人联系电话
//    private String agriculturalCardNo;//	String	N	医保类型(0 新农合  1职工医保 3居民医保 4其他)
//    private String isHouseholder;//	String	Y	是否是户主
//    private String personID;//	String	y	个人健康档案唯一标识符
//    private String no;//	String	y	健康档案号
//    private String isRelation;//	String	y	是否关联亲属（0未关联1关联)
//    private String filingStatue;//	String	y	包是否完成 0 未完成，1已完成
//    private String recordMode;//	String	Y	录入方式（1手动、2农合卡、3医保卡、4身份证）
//    private String inputDeptCode;//	String	N	行政机构编码
//    private String inputDeptName;//	String	N	行政机构名称
//    private String areaCode;//	String	N	地区编码
//    private String areaName;//	String	N	地区名称
//    private String addUserCode;//	String	N	创建人编码
//    private String addUserName;//	String	N	创建人名称
//    private String addOrgId;//	String	N	添加机构ID
//    private String addDT;//	String	N	录入时间
//    private String updateUserCode;//	String	Y	修改人编码
//    private String updateUserName;//	String	Y	修改人名称
//    private String updateOrgId;//	String	Y	修改机构ID
//    private String updateDT;//	String	Y	修改时间
//    private String isFilingStatue;//	String	y	是否关联健康档案（0未关联1已关联）
//    private String personMold;//	String	y	人员类型 （详细解释在后面）
//    private String docOrganizationKey;//	String	N	家庭医生机构主键 (公卫中的机构）
//    private String docOrganizationName;//	String	N	家庭医生机构名称
//    private String docLoginName;//	String	N	签约医生公卫系统登录帐号
//    private String serviceContent;//	String	N	服务内容
//    private String isExecute;//	String	N	是否执行(0执行1未执行)
//    private String docUserID;//	String	N	家庭医生UserID  注：为基本公共卫生里面的签约医生ID
//    private String isCharge;//	String	N	是否已收费（1是，0否）
//    private String actualPackageSumFee;//	String	N	实收金额总计(not null)
//    private String packSumFee;//	String	N	费用总计
//    private String newRuralCMSFee;//	String	N	新农合补偿金额总计(not null)
//    private String otherReduceFee;//	String	N	减免金额总计(not null)
//    private String shouldSelfFee;//	String	N	应自付金额总计(not null)
//    private String jbggwsState;//	String	N	是否是基本公共卫生(0 是  1 否)
//    private String signPageYear;//	String	N	签约包年份
//    private String startExecData;//	String	N	开始执行时间
//    private String endExecData;//	String	N	结束执行时间
//    private String death;//	String	Y	死亡状态（1 未死亡 0死亡）
//    private String familySysno;//	String	y	家庭编码
//    private String memberSysno;//	String	y	人员编码
//    private String interfaceStatus;//	String	N	1 代表关联农合成功 2 代表未关联农合成功
//    private String basicPubilcMoney;//	String	y	公共卫生承担金额
//    private String isPrintyjj;//	String	y	是否打印签约协议（1是0否）
//    private String idType;//	String	y	报补类型（农合和医报）
//
//    private List<SignSeverPakesBeanList> detailInVoList;
////     集合
////    签约明细
////
////    key String
////
////    N 服务包明细主键(xx-xx-xx-xx-xx) UUID 格式为
////(D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
////
////    nhcompensateProjName String
////    N 服务包类型（1:基础包 2:初级包 3:中级包 4:高级包 5其他）
//
//    public IndexAndOnlineSignInitialData() {
//        super();
//    }
//
//    public IndexAndOnlineSignInitialData(int index, String key, String fdSigningDoctorMode, String statusCode, String serverPackageName, String isPersonality, String signDT, String signMode, String signHomeCode, String signDeptName, String signDeptCode, String signDeptPhone, String teamName, String teamCode, String signTeamHeaderName, String signTeamHeaderCode, String signTeamHeaderPhone, String doctorName, String doctorCode, String doctorPhone, String userName, String sexCode, String cardNo, String guardianCardNo, String isUseGuardian, String phone, String agriculturalCardNo, String isHouseholder, String personID, String no, String isRelation, String filingStatue, String recordMode, String inputDeptCode, String inputDeptName, String areaCode, String areaName, String addUserCode, String addUserName, String addOrgId, String addDT, String updateUserCode, String updateUserName, String updateOrgId, String updateDT, String isFilingStatue, String personMold, String docOrganizationKey, String docOrganizationName, String docLoginName, String serviceContent, String isExecute, String docUserID, String isCharge, String actualPackageSumFee, String packSumFee, String newRuralCMSFee, String otherReduceFee, String shouldSelfFee, String jbggwsState, String signPageYear, String startExecData, String endExecData, String death, String familySysno, String memberSysno, String interfaceStatus, String basicPubilcMoney, String isPrintyjj, String idType, List<SignSeverPakesBeanList> detailInVoList) {
//        this.index = index;
//        this.key = key;
//        this.fdSigningDoctorMode = fdSigningDoctorMode;
//        this.statusCode = statusCode;
//        this.serverPackageName = serverPackageName;
//        this.isPersonality = isPersonality;
//        this.signDT = signDT;
//        this.signMode = signMode;
//        this.signHomeCode = signHomeCode;
//        this.signDeptName = signDeptName;
//        this.signDeptCode = signDeptCode;
//        this.signDeptPhone = signDeptPhone;
//        this.teamName = teamName;
//        this.teamCode = teamCode;
//        this.signTeamHeaderName = signTeamHeaderName;
//        this.signTeamHeaderCode = signTeamHeaderCode;
//        this.signTeamHeaderPhone = signTeamHeaderPhone;
//        this.doctorName = doctorName;
//        this.doctorCode = doctorCode;
//        this.doctorPhone = doctorPhone;
//        this.userName = userName;
//        this.sexCode = sexCode;
//        this.cardNo = cardNo;
//        this.guardianCardNo = guardianCardNo;
//        this.isUseGuardian = isUseGuardian;
//        this.phone = phone;
//        this.agriculturalCardNo = agriculturalCardNo;
//        this.isHouseholder = isHouseholder;
//        this.personID = personID;
//        this.no = no;
//        this.isRelation = isRelation;
//        this.filingStatue = filingStatue;
//        this.recordMode = recordMode;
//        this.inputDeptCode = inputDeptCode;
//        this.inputDeptName = inputDeptName;
//        this.areaCode = areaCode;
//        this.areaName = areaName;
//        this.addUserCode = addUserCode;
//        this.addUserName = addUserName;
//        this.addOrgId = addOrgId;
//        this.addDT = addDT;
//        this.updateUserCode = updateUserCode;
//        this.updateUserName = updateUserName;
//        this.updateOrgId = updateOrgId;
//        this.updateDT = updateDT;
//        this.isFilingStatue = isFilingStatue;
//        this.personMold = personMold;
//        this.docOrganizationKey = docOrganizationKey;
//        this.docOrganizationName = docOrganizationName;
//        this.docLoginName = docLoginName;
//        this.serviceContent = serviceContent;
//        this.isExecute = isExecute;
//        this.docUserID = docUserID;
//        this.isCharge = isCharge;
//        this.actualPackageSumFee = actualPackageSumFee;
//        this.packSumFee = packSumFee;
//        this.newRuralCMSFee = newRuralCMSFee;
//        this.otherReduceFee = otherReduceFee;
//        this.shouldSelfFee = shouldSelfFee;
//        this.jbggwsState = jbggwsState;
//        this.signPageYear = signPageYear;
//        this.startExecData = startExecData;
//        this.endExecData = endExecData;
//        this.death = death;
//        this.familySysno = familySysno;
//        this.memberSysno = memberSysno;
//        this.interfaceStatus = interfaceStatus;
//        this.basicPubilcMoney = basicPubilcMoney;
//        this.isPrintyjj = isPrintyjj;
//        this.idType = idType;
//        this.detailInVoList = detailInVoList;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public String getFdSigningDoctorMode() {
//        return fdSigningDoctorMode;
//    }
//
//    public void setFdSigningDoctorMode(String fdSigningDoctorMode) {
//        this.fdSigningDoctorMode = fdSigningDoctorMode;
//    }
//
//    public String getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(String statusCode) {
//        this.statusCode = statusCode;
//    }
//
//    public String getServerPackageName() {
//        return serverPackageName;
//    }
//
//    public void setServerPackageName(String serverPackageName) {
//        this.serverPackageName = serverPackageName;
//    }
//
//    public String getIsPersonality() {
//        return isPersonality;
//    }
//
//    public void setIsPersonality(String isPersonality) {
//        this.isPersonality = isPersonality;
//    }
//
//    public String getSignDT() {
//        return signDT;
//    }
//
//    public void setSignDT(String signDT) {
//        this.signDT = signDT;
//    }
//
//    public String getSignMode() {
//        return signMode;
//    }
//
//    public void setSignMode(String signMode) {
//        this.signMode = signMode;
//    }
//
//    public String getSignHomeCode() {
//        return signHomeCode;
//    }
//
//    public void setSignHomeCode(String signHomeCode) {
//        this.signHomeCode = signHomeCode;
//    }
//
//    public String getSignDeptName() {
//        return signDeptName;
//    }
//
//    public void setSignDeptName(String signDeptName) {
//        this.signDeptName = signDeptName;
//    }
//
//    public String getSignDeptCode() {
//        return signDeptCode;
//    }
//
//    public void setSignDeptCode(String signDeptCode) {
//        this.signDeptCode = signDeptCode;
//    }
//
//    public String getSignDeptPhone() {
//        return signDeptPhone;
//    }
//
//    public void setSignDeptPhone(String signDeptPhone) {
//        this.signDeptPhone = signDeptPhone;
//    }
//
//    public String getTeamName() {
//        return teamName;
//    }
//
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }
//
//    public String getTeamCode() {
//        return teamCode;
//    }
//
//    public void setTeamCode(String teamCode) {
//        this.teamCode = teamCode;
//    }
//
//    public String getSignTeamHeaderName() {
//        return signTeamHeaderName;
//    }
//
//    public void setSignTeamHeaderName(String signTeamHeaderName) {
//        this.signTeamHeaderName = signTeamHeaderName;
//    }
//
//    public String getSignTeamHeaderCode() {
//        return signTeamHeaderCode;
//    }
//
//    public void setSignTeamHeaderCode(String signTeamHeaderCode) {
//        this.signTeamHeaderCode = signTeamHeaderCode;
//    }
//
//    public String getSignTeamHeaderPhone() {
//        return signTeamHeaderPhone;
//    }
//
//    public void setSignTeamHeaderPhone(String signTeamHeaderPhone) {
//        this.signTeamHeaderPhone = signTeamHeaderPhone;
//    }
//
//    public String getDoctorName() {
//        return doctorName;
//    }
//
//    public void setDoctorName(String doctorName) {
//        this.doctorName = doctorName;
//    }
//
//    public String getDoctorCode() {
//        return doctorCode;
//    }
//
//    public void setDoctorCode(String doctorCode) {
//        this.doctorCode = doctorCode;
//    }
//
//    public String getDoctorPhone() {
//        return doctorPhone;
//    }
//
//    public void setDoctorPhone(String doctorPhone) {
//        this.doctorPhone = doctorPhone;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getSexCode() {
//        return sexCode;
//    }
//
//    public void setSexCode(String sexCode) {
//        this.sexCode = sexCode;
//    }
//
//    public String getCardNo() {
//        return cardNo;
//    }
//
//    public void setCardNo(String cardNo) {
//        this.cardNo = cardNo;
//    }
//
//    public String getGuardianCardNo() {
//        return guardianCardNo;
//    }
//
//    public void setGuardianCardNo(String guardianCardNo) {
//        this.guardianCardNo = guardianCardNo;
//    }
//
//    public String getIsUseGuardian() {
//        return isUseGuardian;
//    }
//
//    public void setIsUseGuardian(String isUseGuardian) {
//        this.isUseGuardian = isUseGuardian;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAgriculturalCardNo() {
//        return agriculturalCardNo;
//    }
//
//    public void setAgriculturalCardNo(String agriculturalCardNo) {
//        this.agriculturalCardNo = agriculturalCardNo;
//    }
//
//    public String getIsHouseholder() {
//        return isHouseholder;
//    }
//
//    public void setIsHouseholder(String isHouseholder) {
//        this.isHouseholder = isHouseholder;
//    }
//
//    public String getPersonID() {
//        return personID;
//    }
//
//    public void setPersonID(String personID) {
//        this.personID = personID;
//    }
//
//    public String getNo() {
//        return no;
//    }
//
//    public void setNo(String no) {
//        this.no = no;
//    }
//
//    public String getIsRelation() {
//        return isRelation;
//    }
//
//    public void setIsRelation(String isRelation) {
//        this.isRelation = isRelation;
//    }
//
//    public String getFilingStatue() {
//        return filingStatue;
//    }
//
//    public void setFilingStatue(String filingStatue) {
//        this.filingStatue = filingStatue;
//    }
//
//    public String getRecordMode() {
//        return recordMode;
//    }
//
//    public void setRecordMode(String recordMode) {
//        this.recordMode = recordMode;
//    }
//
//    public String getInputDeptCode() {
//        return inputDeptCode;
//    }
//
//    public void setInputDeptCode(String inputDeptCode) {
//        this.inputDeptCode = inputDeptCode;
//    }
//
//    public String getInputDeptName() {
//        return inputDeptName;
//    }
//
//    public void setInputDeptName(String inputDeptName) {
//        this.inputDeptName = inputDeptName;
//    }
//
//    public String getAreaCode() {
//        return areaCode;
//    }
//
//    public void setAreaCode(String areaCode) {
//        this.areaCode = areaCode;
//    }
//
//    public String getAreaName() {
//        return areaName;
//    }
//
//    public void setAreaName(String areaName) {
//        this.areaName = areaName;
//    }
//
//    public String getAddUserCode() {
//        return addUserCode;
//    }
//
//    public void setAddUserCode(String addUserCode) {
//        this.addUserCode = addUserCode;
//    }
//
//    public String getAddUserName() {
//        return addUserName;
//    }
//
//    public void setAddUserName(String addUserName) {
//        this.addUserName = addUserName;
//    }
//
//    public String getAddOrgId() {
//        return addOrgId;
//    }
//
//    public void setAddOrgId(String addOrgId) {
//        this.addOrgId = addOrgId;
//    }
//
//    public String getAddDT() {
//        return addDT;
//    }
//
//    public void setAddDT(String addDT) {
//        this.addDT = addDT;
//    }
//
//    public String getUpdateUserCode() {
//        return updateUserCode;
//    }
//
//    public void setUpdateUserCode(String updateUserCode) {
//        this.updateUserCode = updateUserCode;
//    }
//
//    public String getUpdateUserName() {
//        return updateUserName;
//    }
//
//    public void setUpdateUserName(String updateUserName) {
//        this.updateUserName = updateUserName;
//    }
//
//    public String getUpdateOrgId() {
//        return updateOrgId;
//    }
//
//    public void setUpdateOrgId(String updateOrgId) {
//        this.updateOrgId = updateOrgId;
//    }
//
//    public String getUpdateDT() {
//        return updateDT;
//    }
//
//    public void setUpdateDT(String updateDT) {
//        this.updateDT = updateDT;
//    }
//
//    public String getIsFilingStatue() {
//        return isFilingStatue;
//    }
//
//    public void setIsFilingStatue(String isFilingStatue) {
//        this.isFilingStatue = isFilingStatue;
//    }
//
//    public String getPersonMold() {
//        return personMold;
//    }
//
//    public void setPersonMold(String personMold) {
//        this.personMold = personMold;
//    }
//
//    public String getDocOrganizationKey() {
//        return docOrganizationKey;
//    }
//
//    public void setDocOrganizationKey(String docOrganizationKey) {
//        this.docOrganizationKey = docOrganizationKey;
//    }
//
//    public String getDocOrganizationName() {
//        return docOrganizationName;
//    }
//
//    public void setDocOrganizationName(String docOrganizationName) {
//        this.docOrganizationName = docOrganizationName;
//    }
//
//    public String getDocLoginName() {
//        return docLoginName;
//    }
//
//    public void setDocLoginName(String docLoginName) {
//        this.docLoginName = docLoginName;
//    }
//
//    public String getServiceContent() {
//        return serviceContent;
//    }
//
//    public void setServiceContent(String serviceContent) {
//        this.serviceContent = serviceContent;
//    }
//
//    public String getIsExecute() {
//        return isExecute;
//    }
//
//    public void setIsExecute(String isExecute) {
//        this.isExecute = isExecute;
//    }
//
//    public String getDocUserID() {
//        return docUserID;
//    }
//
//    public void setDocUserID(String docUserID) {
//        this.docUserID = docUserID;
//    }
//
//    public String getIsCharge() {
//        return isCharge;
//    }
//
//    public void setIsCharge(String isCharge) {
//        this.isCharge = isCharge;
//    }
//
//    public String getActualPackageSumFee() {
//        return actualPackageSumFee;
//    }
//
//    public void setActualPackageSumFee(String actualPackageSumFee) {
//        this.actualPackageSumFee = actualPackageSumFee;
//    }
//
//    public String getPackSumFee() {
//        return packSumFee;
//    }
//
//    public void setPackSumFee(String packSumFee) {
//        this.packSumFee = packSumFee;
//    }
//
//    public String getNewRuralCMSFee() {
//        return newRuralCMSFee;
//    }
//
//    public void setNewRuralCMSFee(String newRuralCMSFee) {
//        this.newRuralCMSFee = newRuralCMSFee;
//    }
//
//    public String getOtherReduceFee() {
//        return otherReduceFee;
//    }
//
//    public void setOtherReduceFee(String otherReduceFee) {
//        this.otherReduceFee = otherReduceFee;
//    }
//
//    public String getShouldSelfFee() {
//        return shouldSelfFee;
//    }
//
//    public void setShouldSelfFee(String shouldSelfFee) {
//        this.shouldSelfFee = shouldSelfFee;
//    }
//
//    public String getJbggwsState() {
//        return jbggwsState;
//    }
//
//    public void setJbggwsState(String jbggwsState) {
//        this.jbggwsState = jbggwsState;
//    }
//
//    public String getSignPageYear() {
//        return signPageYear;
//    }
//
//    public void setSignPageYear(String signPageYear) {
//        this.signPageYear = signPageYear;
//    }
//
//    public String getStartExecData() {
//        return startExecData;
//    }
//
//    public void setStartExecData(String startExecData) {
//        this.startExecData = startExecData;
//    }
//
//    public String getEndExecData() {
//        return endExecData;
//    }
//
//    public void setEndExecData(String endExecData) {
//        this.endExecData = endExecData;
//    }
//
//    public String getDeath() {
//        return death;
//    }
//
//    public void setDeath(String death) {
//        this.death = death;
//    }
//
//    public String getFamilySysno() {
//        return familySysno;
//    }
//
//    public void setFamilySysno(String familySysno) {
//        this.familySysno = familySysno;
//    }
//
//    public String getMemberSysno() {
//        return memberSysno;
//    }
//
//    public void setMemberSysno(String memberSysno) {
//        this.memberSysno = memberSysno;
//    }
//
//    public String getInterfaceStatus() {
//        return interfaceStatus;
//    }
//
//    public void setInterfaceStatus(String interfaceStatus) {
//        this.interfaceStatus = interfaceStatus;
//    }
//
//    public String getBasicPubilcMoney() {
//        return basicPubilcMoney;
//    }
//
//    public void setBasicPubilcMoney(String basicPubilcMoney) {
//        this.basicPubilcMoney = basicPubilcMoney;
//    }
//
//    public String getIsPrintyjj() {
//        return isPrintyjj;
//    }
//
//    public void setIsPrintyjj(String isPrintyjj) {
//        this.isPrintyjj = isPrintyjj;
//    }
//
//    public String getIdType() {
//        return idType;
//    }
//
//    public void setIdType(String idType) {
//        this.idType = idType;
//    }
//
//    public List<SignSeverPakesBeanList> getDetailInVoList() {
//        return detailInVoList;
//    }
//
//    public void setDetailInVoList(List<SignSeverPakesBeanList> detailInVoList) {
//        this.detailInVoList = detailInVoList;
//    }
}
