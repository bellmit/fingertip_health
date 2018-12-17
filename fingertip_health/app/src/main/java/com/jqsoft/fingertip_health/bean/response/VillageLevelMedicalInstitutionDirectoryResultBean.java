package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-26.
 */

public class VillageLevelMedicalInstitutionDirectoryResultBean {
    private String count;
    private String key;
    private String orgCode;
    private String orgName;    //医疗人员个数
    public String docName;
    public String docPhone;

    public VillageLevelMedicalInstitutionDirectoryResultBean() {
        super();
    }

    public VillageLevelMedicalInstitutionDirectoryResultBean(String count, String key, String orgCode, String orgName) {
        this.count = count;
        this.key = key;
        this.orgCode = orgCode;
        this.orgName = orgName;
    }

    public VillageLevelMedicalInstitutionDirectoryResultBean(String count, String key, String orgCode, String orgName, String docName, String docPhone) {
        this.count = count;
        this.key = key;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.docName = docName;
        this.docPhone = docPhone;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
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
}
