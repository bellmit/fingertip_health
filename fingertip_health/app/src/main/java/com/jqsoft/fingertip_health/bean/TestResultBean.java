package com.jqsoft.fingertip_health.bean;

import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;

/**
 * Created by Administrator on 2017-06-30.
 */

public class TestResultBean {
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

    private LoginResultBean2 loginResultBean2;
    public TestResultBean() {
        super();
    }

    public TestResultBean(String guserid, String sloginname, String susername, String sphone, String ssexname, String shiploginname, String sorganizationkey, String sorgInstitutioncode, String sorganizationname, String sorganizationtypecode, String sorganizationlevelcode, String smanagementdivisioncode, String smanagementdivisionname, LoginResultBean2 loginResultBean2) {
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
        this.loginResultBean2 = loginResultBean2;
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

    public LoginResultBean2 getLoginResultBean2() {
        return loginResultBean2;
    }

    public void setLoginResultBean2(LoginResultBean2 loginResultBean2) {
        this.loginResultBean2 = loginResultBean2;
    }
}
