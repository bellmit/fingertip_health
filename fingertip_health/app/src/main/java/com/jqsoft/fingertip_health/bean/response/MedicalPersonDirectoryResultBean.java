package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-26.
 */

public class MedicalPersonDirectoryResultBean {
    private String docName;
    private String docPhone;
    private String key;
    private String orgName;

    public MedicalPersonDirectoryResultBean() {
        super();
    }

    public MedicalPersonDirectoryResultBean(String docName, String docPhone, String key, String orgName) {
        this.docName = docName;
        this.docPhone = docPhone;
        this.key = key;
        this.orgName = orgName;
    }


    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPhone() {
        return docPhone;
    }

    public void setDocPhone(String docPhone) {
        this.docPhone = docPhone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}
