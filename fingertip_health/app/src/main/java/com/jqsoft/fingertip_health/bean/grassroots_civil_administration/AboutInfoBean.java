package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Mars on 2018/1/31.
 */

public class AboutInfoBean {
    private String contractUs;
    private String disclaimer;
    private String technicalSupport;
    private String address;
    private String phone;

    public AboutInfoBean() {
    }

    public AboutInfoBean(String contractUs, String disclaimer, String technicalSupport, String address, String phone) {
        this.contractUs = contractUs;
        this.disclaimer = disclaimer;
        this.technicalSupport = technicalSupport;
        this.address = address;
        this.phone = phone;
    }

    public String getContractUs() {
        return contractUs;
    }

    public void setContractUs(String contractUs) {
        this.contractUs = contractUs;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getTechnicalSupport() {
        return technicalSupport;
    }

    public void setTechnicalSupport(String technicalSupport) {
        this.technicalSupport = technicalSupport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
