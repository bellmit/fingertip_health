package com.jqsoft.fingertip_health.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017-06-21.
 */

public class SignAndHonourAgreementResultBean {
    private String clinicName;//卫生室名称
    private String year;//年度
    private List<SignNumberAndRatioBean> signNumberAndRatioList;
    private String mySignedNumber;//我的签约人数
    private String numberOfProjectsNeedToExecute;//近7天需要执行项目数
    private String numberOfProjectsTimeout;//超时未执行项目数
    private String numberOfNewServiceAssess;//新服务评价信息条数
    private String numberOfNewSignedApplication;//新签约申请条数
    private String numberOfAppointmentSignServiceService;//预约签约服务信息条数
    public SignAndHonourAgreementResultBean() {
        super();
    }
    public SignAndHonourAgreementResultBean(String clinicName, String year, List<SignNumberAndRatioBean> signNumberAndRatioList, String mySignedNumber, String numberOfProjectsNeedToExecute, String numberOfProjectsTimeout, String numberOfNewServiceAssess, String numberOfNewSignedApplication, String numberOfAppointmentSignServiceService) {

        this.clinicName = clinicName;
        this.year = year;
        this.signNumberAndRatioList = signNumberAndRatioList;
        this.mySignedNumber = mySignedNumber;
        this.numberOfProjectsNeedToExecute = numberOfProjectsNeedToExecute;
        this.numberOfProjectsTimeout = numberOfProjectsTimeout;
        this.numberOfNewServiceAssess = numberOfNewServiceAssess;
        this.numberOfNewSignedApplication = numberOfNewSignedApplication;
        this.numberOfAppointmentSignServiceService = numberOfAppointmentSignServiceService;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<SignNumberAndRatioBean> getSignNumberAndRatioList() {
        return signNumberAndRatioList;
    }

    public void setSignNumberAndRatioList(List<SignNumberAndRatioBean> signNumberAndRatioList) {
        this.signNumberAndRatioList = signNumberAndRatioList;
    }

    public String getMySignedNumber() {
        return mySignedNumber;
    }

    public void setMySignedNumber(String mySignedNumber) {
        this.mySignedNumber = mySignedNumber;
    }

    public String getNumberOfProjectsNeedToExecute() {
        return numberOfProjectsNeedToExecute;
    }

    public void setNumberOfProjectsNeedToExecute(String numberOfProjectsNeedToExecute) {
        this.numberOfProjectsNeedToExecute = numberOfProjectsNeedToExecute;
    }

    public String getNumberOfProjectsTimeout() {
        return numberOfProjectsTimeout;
    }

    public void setNumberOfProjectsTimeout(String numberOfProjectsTimeout) {
        this.numberOfProjectsTimeout = numberOfProjectsTimeout;
    }

    public String getNumberOfNewServiceAssess() {
        return numberOfNewServiceAssess;
    }

    public void setNumberOfNewServiceAssess(String numberOfNewServiceAssess) {
        this.numberOfNewServiceAssess = numberOfNewServiceAssess;
    }

    public String getNumberOfNewSignedApplication() {
        return numberOfNewSignedApplication;
    }

    public void setNumberOfNewSignedApplication(String numberOfNewSignedApplication) {
        this.numberOfNewSignedApplication = numberOfNewSignedApplication;
    }

    public String getNumberOfAppointmentSignServiceService() {
        return numberOfAppointmentSignServiceService;
    }

    public void setNumberOfAppointmentSignServiceService(String numberOfAppointmentSignServiceService) {
        this.numberOfAppointmentSignServiceService = numberOfAppointmentSignServiceService;
    }

}
