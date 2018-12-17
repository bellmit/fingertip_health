package com.jqsoft.fingertip_health.bean.response_new;

/**
 * Created by Administrator on 2017-06-27.
 */

public class LoginResultBean2 {
    private String guserid;//                     登陆用户主键
    private String sloginname;//   	              登录名
    private String susername;//   	              用户姓名
    private String sphone;//                       电话
    private String ssexname;//                     性别
    private String shiploginname;//                平台用户名
    private String sorganizationkey;// 	           机构主键【用户所在机构，下同】
    private String sorgInstitutioncode;//            组织机构代码
    private String sorganizationname;//             机构名称
    private String sorganizationtypecode;//         机构类型编码
    private String sorganizationlevelcode;//         机构级别编码
    private String smanagementdivisioncode;//      管理区划编码
    private String smanagementdivisionname;//      管理区划名称
    private String cardNo;//                       身份证号码
    private String alias;//                       别名
    private String fdIsDoctorServiceTime;
    private String areaCountryName;
    private String age;
    // （签约的服务时间生效(
    //      1:服务时间及时生效)
//            (2: 服务时间下年生效)
//            (3: 服务时间当年生效)
//            (4: 服务时间上年生效)
    private String fdSigningDoctorMode;//              （签约方式： 1：按年度签约  2：随到随签）
    private String isEnablePerson;//                  是否启用调阅档案0 不启用  1 启用
    private String isEnableAgricultura;//               是否启用农合0 不启用  1 启用
    private String isEnableInput;//              是否启用手动录入0 不启用  1 启用
    private String isEnableReadCard;//             是否启用读身份证按钮0 不启用  1 启用


    public LoginResultBean2() {
        super();
    }

    public LoginResultBean2(String guserid, String sloginname, String susername, String sphone, String ssexname, String shiploginname, String sorganizationkey, String sorgInstitutioncode, String sorganizationname, String sorganizationtypecode, String sorganizationlevelcode, String smanagementdivisioncode, String smanagementdivisionname, String cardNo, String alias, String fdIsDoctorServiceTime, String fdSigningDoctorMode, String isEnablePerson, String isEnableAgricultura, String isEnableInput, String isEnableReadCard, String areaCountryName, String age) {
        this.guserid = guserid;
        this.sloginname = sloginname;
        this.susername = susername;
        this.sphone = sphone;
        this.ssexname = ssexname;
        this.shiploginname = shiploginname;
        this.sorganizationkey = sorganizationkey;
        this.sorgInstitutioncode = sorgInstitutioncode;
        this.sorganizationname = sorganizationname;
        this.sorganizationtypecode = sorganizationtypecode;
        this.sorganizationlevelcode = sorganizationlevelcode;
        this.smanagementdivisioncode = smanagementdivisioncode;
        this.smanagementdivisionname = smanagementdivisionname;
        this.cardNo = cardNo;
        this.alias = alias;
        this.fdIsDoctorServiceTime = fdIsDoctorServiceTime;
        this.fdSigningDoctorMode = fdSigningDoctorMode;
        this.isEnablePerson = isEnablePerson;
        this.isEnableAgricultura = isEnableAgricultura;
        this.isEnableInput = isEnableInput;
        this.isEnableReadCard = isEnableReadCard;
        this.areaCountryName = areaCountryName;
        this.age = age;
    }


    public String getGuserid() {
        return guserid;
    }

    public void setGuserid(String guserid) {
        this.guserid = guserid;
    }

    public String getSloginname() {
        return sloginname;
    }

    public void setSloginname(String sloginname) {
        this.sloginname = sloginname;
    }

    public String getSusername() {
        return susername;
    }

    public void setSusername(String susername) {
        this.susername = susername;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSsexname() {
        return ssexname;
    }

    public void setSsexname(String ssexname) {
        this.ssexname = ssexname;
    }

    public String getShiploginname() {
        return shiploginname;
    }

    public void setShiploginname(String shiploginname) {
        this.shiploginname = shiploginname;
    }

    public String getSorganizationkey() {
        return sorganizationkey;
    }

    public void setSorganizationkey(String sorganizationkey) {
        this.sorganizationkey = sorganizationkey;
    }

    public String getSorgInstitutioncode() {
        return sorgInstitutioncode;
    }

    public void setSorgInstitutioncode(String sorgInstitutioncode) {
        this.sorgInstitutioncode = sorgInstitutioncode;
    }

    public String getSorganizationname() {
        return sorganizationname;
    }

    public void setSorganizationname(String sorganizationname) {
        this.sorganizationname = sorganizationname;
    }

    public String getSorganizationtypecode() {
        return sorganizationtypecode;
    }

    public void setSorganizationtypecode(String sorganizationtypecode) {
        this.sorganizationtypecode = sorganizationtypecode;
    }

    public String getSorganizationlevelcode() {
        return sorganizationlevelcode;
    }

    public void setSorganizationlevelcode(String sorganizationlevelcode) {
        this.sorganizationlevelcode = sorganizationlevelcode;
    }

    public String getSmanagementdivisioncode() {
        return smanagementdivisioncode;
    }

    public void setSmanagementdivisioncode(String smanagementdivisioncode) {
        this.smanagementdivisioncode = smanagementdivisioncode;
    }

    public String getSmanagementdivisionname() {
        return smanagementdivisionname;
    }

    public void setSmanagementdivisionname(String smanagementdivisionname) {
        this.smanagementdivisionname = smanagementdivisionname;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFdIsDoctorServiceTime() {
        return fdIsDoctorServiceTime;
    }

    public void setFdIsDoctorServiceTime(String fdIsDoctorServiceTime) {
        this.fdIsDoctorServiceTime = fdIsDoctorServiceTime;
    }

    public String getFdSigningDoctorMode() {
        return fdSigningDoctorMode;
    }

    public void setFdSigningDoctorMode(String fdSigningDoctorMode) {
        this.fdSigningDoctorMode = fdSigningDoctorMode;
    }
    public String getIsEnablePerson() {
        return isEnablePerson;
    }

    public void setIsEnablePerson(String isEnablePerson) {
        this.isEnablePerson = isEnablePerson;
    }

    public String getIsEnableAgricultura() {
        return isEnableAgricultura;
    }

    public void setIsEnableAgricultura(String isEnableAgricultura) {
        this.isEnableAgricultura = isEnableAgricultura;
    }

    public String getIsEnableInput() {
        return isEnableInput;
    }

    public void setIsEnableInput(String isEnableInput) {
        this.isEnableInput = isEnableInput;
    }

    public String getIsEnableReadCard() {
        return isEnableReadCard;
    }

    public void setIsEnableReadCard(String isEnableReadCard) {
        this.isEnableReadCard = isEnableReadCard;
    }
    public String getAreaCountryName() {
        return areaCountryName;
    }

    public void setAreaCountryName(String areaCountryName) {
        this.areaCountryName = areaCountryName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
