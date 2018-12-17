package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mars on 2018/9/14.
 */

public class DeatailPeopleBean implements Serializable {
    private  String signDoctorPhone;
    private  String agreementName;
    private  String name;
    private List<ServicePackage> ServicePackageList;
    private  String idNo;
    private  String content;
    private  String signDoctorName;

    public DeatailPeopleBean() {
    }

    public DeatailPeopleBean(String signDoctorPhone, String agreementName, String name, List<ServicePackage> servicePackageList, String idNo, String content, String signDoctorName) {
        this.signDoctorPhone = signDoctorPhone;
        this.agreementName = agreementName;
        this.name = name;
        ServicePackageList = servicePackageList;
        this.idNo = idNo;
        this.content = content;
        this.signDoctorName = signDoctorName;
    }

    public String getSignDoctorPhone() {
        return signDoctorPhone;
    }

    public void setSignDoctorPhone(String signDoctorPhone) {
        this.signDoctorPhone = signDoctorPhone;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServicePackage> getServicePackageList() {
        return ServicePackageList;
    }

    public void setServicePackageList(List<ServicePackage> servicePackageList) {
        ServicePackageList = servicePackageList;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignDoctorName() {
        return signDoctorName;
    }

    public void setSignDoctorName(String signDoctorName) {
        this.signDoctorName = signDoctorName;
    }
}
