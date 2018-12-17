package com.jqsoft.fingertip_health.bean.response_new;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-05.
 */
//签约服务评价
public class SignClientServiceAssessResultBean implements Serializable{
    private String serviceContentKey;
    private String serviceContentItemsKey;
    private String serviceItemsName;
    private String execOfficer;
    private String signDetailID;
    private String shouldExecTimes;
    private String hadExecTimes;
    private String startExecData;
    private String endExecData;
    private String finished;
    private String fwmc;
    private String serviceClassSort;
    private String execTime;//拒绝执行次数
    private String servicePlanID;
    private String itemsKey;
    private String executeDT;
    private String doctorCode;
    private String orgID;
    private String docUserID;
    private String evaluationState;
    private String signPageyear;
    public SignClientServiceAssessResultBean() {
    }

    public SignClientServiceAssessResultBean(String serviceContentKey, String serviceContentItemsKey, String serviceItemsName, String execOfficer, String signDetailID, String shouldExecTimes, String hadExecTimes, String startExecData, String endExecData, String finished, String fwmc, String serviceClassSort, String execTime, String servicePlanID, String itemsKey, String executeDT, String doctorCode, String orgID, String docUserID, String evaluationState, String signPageyear) {
        this.serviceContentKey = serviceContentKey;
        this.serviceContentItemsKey = serviceContentItemsKey;
        this.serviceItemsName = serviceItemsName;
        this.execOfficer = execOfficer;
        this.signDetailID = signDetailID;
        this.shouldExecTimes = shouldExecTimes;
        this.hadExecTimes = hadExecTimes;
        this.startExecData = startExecData;
        this.endExecData = endExecData;
        this.finished = finished;
        this.fwmc = fwmc;
        this.serviceClassSort = serviceClassSort;
        this.execTime = execTime;
        this.servicePlanID = servicePlanID;
        this.itemsKey = itemsKey;
        this.executeDT = executeDT;
        this.doctorCode = doctorCode;
        this.orgID = orgID;
        this.docUserID = docUserID;
        this.evaluationState = evaluationState;
        this.signPageyear = signPageyear;
    }

    public String getServiceContentKey() {
        return serviceContentKey;
    }

    public void setServiceContentKey(String serviceContentKey) {
        this.serviceContentKey = serviceContentKey;
    }

    public String getServiceContentItemsKey() {
        return serviceContentItemsKey;
    }

    public void setServiceContentItemsKey(String serviceContentItemsKey) {
        this.serviceContentItemsKey = serviceContentItemsKey;
    }

    public String getServiceItemsName() {
        return serviceItemsName;
    }

    public void setServiceItemsName(String serviceItemsName) {
        this.serviceItemsName = serviceItemsName;
    }

    public String getExecOfficer() {
        return execOfficer;
    }

    public void setExecOfficer(String execOfficer) {
        this.execOfficer = execOfficer;
    }

    public String getSignDetailID() {
        return signDetailID;
    }

    public void setSignDetailID(String signDetailID) {
        this.signDetailID = signDetailID;
    }

    public String getShouldExecTimes() {
        return shouldExecTimes;
    }

    public void setShouldExecTimes(String shouldExecTimes) {
        this.shouldExecTimes = shouldExecTimes;
    }

    public String getHadExecTimes() {
        return hadExecTimes;
    }

    public void setHadExecTimes(String hadExecTimes) {
        this.hadExecTimes = hadExecTimes;
    }

    public String getStartExecData() {
        return startExecData;
    }

    public void setStartExecData(String startExecData) {
        this.startExecData = startExecData;
    }

    public String getEndExecData() {
        return endExecData;
    }

    public void setEndExecData(String endExecData) {
        this.endExecData = endExecData;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }


    public String getFwmc() {
        return fwmc;
    }

    public void setFwmc(String fwmc) {
        this.fwmc = fwmc;
    }

    public String getServiceClassSort() {
        return serviceClassSort;
    }

    public void setServiceClassSort(String serviceClassSort) {
        this.serviceClassSort = serviceClassSort;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getServicePlanID() {
        return servicePlanID;
    }

    public void setServicePlanID(String servicePlanID) {
        this.servicePlanID = servicePlanID;
    }

    public String getItemsKey() {
        return itemsKey;
    }

    public void setItemsKey(String itemsKey) {
        this.itemsKey = itemsKey;
    }


    public String getExecuteDT() {
        return executeDT;
    }

    public void setExecuteDT(String executeDT) {
        this.executeDT = executeDT;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getDocUserID() {
        return docUserID;
    }

    public void setDocUserID(String docUserID) {
        this.docUserID = docUserID;
    }

    public String getEvaluationState() {
        return evaluationState;
    }

    public void setEvaluationState(String evaluationState) {
        this.evaluationState = evaluationState;
    }
    public String getSignPageyear() {
        return signPageyear;
    }

    public void setSignPageyear(String signPageyear) {
        this.signPageyear = signPageyear;
    }
}
