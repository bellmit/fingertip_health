package com.jqsoft.fingertip_health.bean.fingertip;//

import java.util.List;

/**
 * Created by Mars on 2018/9/10.
 */

public class PersonPostBean {

   private String  id;//	服务任务	;////String
    private String  name	;//主键	;////String
    private String no	;//档案编号	String
    private String idNo	;//身份证号(yyyy-MM-dd)	String
    private String sexCode	;//性别编码 家庭 3 电话	Integer
    private String sexName;//	性别名称	String
    private String birthday;//	出生日期	String
    private String workUnit;//	工作单位	String
    private String contactPhone;//	本人电话（mmHg）	String
    private String contactName	;//联系人姓名（mmHg）	String
    private String  residentType	;//常驻类型	Integer
    private String       nationCode;//

    private String nationName	;//民族名称	String
    private String abobloodCode;//	血型编码	Integer
    private String  abobloodName;//	血型名称	String
    private String rhbloodName;//	RH血型名称	String
    private String cultureCode;//	文化程度编码	Integer
    private String cultureName	;//文化程度名称	String
    private String occupationCode	;//职业编码	Integer
    private String  occupationName	;//职业名称	String
    private String marryCode	;//婚姻编码	Integer
    private String  insType	;//医疗费用支付方式	String

    List<PersonallergyHistoriesBean> allergyHistories;//药物过敏史（1 良好 2 一般 3 差）	List
    private String  exposureHistor	;//暴露史	String
    List<PersonPastHistoryBean>	pastHistories;//既往史	List
    private String pastHistoryCode	;//疾病既往史code	String

    private String  fatherDisease	;//父亲疾病史	String
    private String motherDisease	;//母亲疾病史	String
    private String brothersDisease	;//兄弟姐妹疾病史	String
    private String childrenDisease	;//子女疾病史	String
    private String hereditaryHistory;//	遗传病史	String
    private String  hereditaryName	;//遗传病史名称	String
    private String deformitySituation;//	残疾情况	String

    private String exhaustFacilit	;//厨房排风设施	String
    private String fuelType	;//燃料类型	String
    private String drinkingWater	;//饮水	String
    private String  toilet	;//厕所	String

    private String poultryEnclosu	;//禽畜栏	String

    private String registerAddress	;//户籍地址	String
    private String phone;//居住组编码	String
    private String areaFulladdress	;//详细地址(从县开始)	String
    private String archivingDoctorDate	;//建档日期	String
    private String archivingDoctorCode	;//建档医生code	String
    private String archivingDoctorName	;//建档医生姓名	String

    private String zuCode	;//居住组编码	String


    public PersonPostBean() {
    }

    public PersonPostBean(String id, String name, String no, String idNo, String sexCode, String sexName, String birthday, String workUnit, String contactPhone, String contactName, String residentType, String nationCode, String nationName, String abobloodCode, String abobloodName, String rhbloodName, String cultureCode, String cultureName, String occupationCode, String occupationName, String marryCode, String insType, List<PersonallergyHistoriesBean> allergyHistories, String exposureHistor, List<PersonPastHistoryBean> pastHistories, String pastHistoryCode, String fatherDisease, String motherDisease, String brothersDisease, String childrenDisease, String hereditaryHistory, String hereditaryName, String deformitySituation, String exhaustFacilit, String fuelType, String drinkingWater, String toilet, String poultryEnclosu, String registerAddress, String zuCode, String areaFulladdress, String archivingDoctorDate, String archivingDoctorCode, String archivingDoctorName) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.idNo = idNo;
        this.sexCode = sexCode;
        this.sexName = sexName;
        this.birthday = birthday;
        this.workUnit = workUnit;
        this.contactPhone = contactPhone;
        this.contactName = contactName;
        this.residentType = residentType;
        this.nationCode = nationCode;
        this.nationName = nationName;
        this.abobloodCode = abobloodCode;
        this.abobloodName = abobloodName;
        this.rhbloodName = rhbloodName;
        this.cultureCode = cultureCode;
        this.cultureName = cultureName;
        this.occupationCode = occupationCode;
        this.occupationName = occupationName;
        this.marryCode = marryCode;
        this.insType = insType;
        this.allergyHistories = allergyHistories;
        this.exposureHistor = exposureHistor;
        this.pastHistories = pastHistories;
        this.pastHistoryCode = pastHistoryCode;
        this.fatherDisease = fatherDisease;
        this.motherDisease = motherDisease;
        this.brothersDisease = brothersDisease;
        this.childrenDisease = childrenDisease;
        this.hereditaryHistory = hereditaryHistory;
        this.hereditaryName = hereditaryName;
        this.deformitySituation = deformitySituation;
        this.exhaustFacilit = exhaustFacilit;
        this.fuelType = fuelType;
        this.drinkingWater = drinkingWater;
        this.toilet = toilet;
        this.poultryEnclosu = poultryEnclosu;
        this.registerAddress = registerAddress;
        this.zuCode = zuCode;
        this.areaFulladdress = areaFulladdress;
        this.archivingDoctorDate = archivingDoctorDate;
        this.archivingDoctorCode = archivingDoctorCode;
        this.archivingDoctorName = archivingDoctorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getAbobloodCode() {
        return abobloodCode;
    }

    public void setAbobloodCode(String abobloodCode) {
        this.abobloodCode = abobloodCode;
    }

    public String getAbobloodName() {
        return abobloodName;
    }

    public void setAbobloodName(String abobloodName) {
        this.abobloodName = abobloodName;
    }

    public String getRhbloodName() {
        return rhbloodName;
    }

    public void setRhbloodName(String rhbloodName) {
        this.rhbloodName = rhbloodName;
    }

    public String getCultureCode() {
        return cultureCode;
    }

    public void setCultureCode(String cultureCode) {
        this.cultureCode = cultureCode;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getMarryCode() {
        return marryCode;
    }

    public void setMarryCode(String marryCode) {
        this.marryCode = marryCode;
    }

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }

    public List<PersonallergyHistoriesBean> getAllergyHistories() {
        return allergyHistories;
    }

    public void setAllergyHistories(List<PersonallergyHistoriesBean> allergyHistories) {
        this.allergyHistories = allergyHistories;
    }

    public String getExposureHistor() {
        return exposureHistor;
    }

    public void setExposureHistor(String exposureHistor) {
        this.exposureHistor = exposureHistor;
    }

    public List<PersonPastHistoryBean> getPastHistories() {
        return pastHistories;
    }

    public void setPastHistories(List<PersonPastHistoryBean> pastHistories) {
        this.pastHistories = pastHistories;
    }

    public String getFatherDisease() {
        return fatherDisease;
    }

    public void setFatherDisease(String fatherDisease) {
        this.fatherDisease = fatherDisease;
    }

    public String getMotherDisease() {
        return motherDisease;
    }

    public void setMotherDisease(String motherDisease) {
        this.motherDisease = motherDisease;
    }

    public String getBrothersDisease() {
        return brothersDisease;
    }

    public void setBrothersDisease(String brothersDisease) {
        this.brothersDisease = brothersDisease;
    }

    public String getChildrenDisease() {
        return childrenDisease;
    }

    public void setChildrenDisease(String childrenDisease) {
        this.childrenDisease = childrenDisease;
    }

    public String getHereditaryHistory() {
        return hereditaryHistory;
    }

    public void setHereditaryHistory(String hereditaryHistory) {
        this.hereditaryHistory = hereditaryHistory;
    }

    public String getHereditaryName() {
        return hereditaryName;
    }

    public void setHereditaryName(String hereditaryName) {
        this.hereditaryName = hereditaryName;
    }

    public String getDeformitySituation() {
        return deformitySituation;
    }

    public void setDeformitySituation(String deformitySituation) {
        this.deformitySituation = deformitySituation;
    }

    public String getExhaustFacilit() {
        return exhaustFacilit;
    }

    public void setExhaustFacilit(String exhaustFacilit) {
        this.exhaustFacilit = exhaustFacilit;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getDrinkingWater() {
        return drinkingWater;
    }

    public void setDrinkingWater(String drinkingWater) {
        this.drinkingWater = drinkingWater;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }

    public String getPoultryEnclosu() {
        return poultryEnclosu;
    }

    public void setPoultryEnclosu(String poultryEnclosu) {
        this.poultryEnclosu = poultryEnclosu;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getZuCode() {
        return zuCode;
    }

    public void setZuCode(String zuCode) {
        this.zuCode = zuCode;
    }

    public String getAreaFulladdress() {
        return areaFulladdress;
    }

    public void setAreaFulladdress(String areaFulladdress) {
        this.areaFulladdress = areaFulladdress;
    }

    public String getArchivingDoctorDate() {
        return archivingDoctorDate;
    }

    public void setArchivingDoctorDate(String archivingDoctorDate) {
        this.archivingDoctorDate = archivingDoctorDate;
    }

    public String getArchivingDoctorCode() {
        return archivingDoctorCode;
    }

    public void setArchivingDoctorCode(String archivingDoctorCode) {
        this.archivingDoctorCode = archivingDoctorCode;
    }

    public String getArchivingDoctorName() {
        return archivingDoctorName;
    }

    public void setArchivingDoctorName(String archivingDoctorName) {
        this.archivingDoctorName = archivingDoctorName;
    }

    public String getPastHistoryCode() {
        return pastHistoryCode;
    }

    public void setPastHistoryCode(String pastHistoryCode) {
        this.pastHistoryCode = pastHistoryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
