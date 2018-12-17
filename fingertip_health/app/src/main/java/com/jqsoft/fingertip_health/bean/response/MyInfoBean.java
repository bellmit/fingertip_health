package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-23.
 */

public class MyInfoBean {
    private String uid;
    private String username;
    private String loginAccount;
    private String loginAlias;
    private String idCardNumber;
    private String institutionName;
    private String contactNumber;
    public MyInfoBean() {
        super();
    }

    public MyInfoBean(String uid, String username, String loginAccount, String loginAlias, String idCardNumber, String institutionName, String contactNumber) {
        this.uid = uid;
        this.username = username;
        this.loginAccount = loginAccount;
        this.loginAlias = loginAlias;
        this.idCardNumber = idCardNumber;
        this.institutionName = institutionName;
        this.contactNumber = contactNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginAlias() {
        return loginAlias;
    }

    public void setLoginAlias(String loginAlias) {
        this.loginAlias = loginAlias;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
