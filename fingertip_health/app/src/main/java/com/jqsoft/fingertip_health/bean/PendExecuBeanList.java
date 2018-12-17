package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

public class PendExecuBeanList implements Serializable {
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
    public String reservationState;

    public String reservationTime;//预约时间
    public String reservationServrtPlace;//预约服务地点
    public String reservationServrtPlaceOther;//预约服务地点其他




    public PendExecuBeanList(String serviceContentKey, String serviceContentItemsKey, String serviceItemsName, String execOfficer, String signDetailID, String shouldExecTimes, String hadExecTimes, String startExecData, String endExecData, String finished, String fwmc, String serviceClassSort,String  executeDT) {
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
        this.executeDT = executeDT;
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

    public PendExecuBeanList() {
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


    @Override
    public boolean equals(Object obj) {
        PendExecuBeanList s=(PendExecuBeanList)obj;
        return serviceContentItemsKey.equals(s.serviceContentItemsKey);
    }
    @Override
    public int hashCode() {
        String in =  serviceContentItemsKey;
        return in.hashCode();
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

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReservationServrtPlace() {
        return reservationServrtPlace;
    }

    public void setReservationServrtPlace(String reservationServrtPlace) {
        this.reservationServrtPlace = reservationServrtPlace;
    }

    public String getReservationServrtPlaceOther() {
        return reservationServrtPlaceOther;
    }

    public void setReservationServrtPlaceOther(String reservationServrtPlaceOther) {
        this.reservationServrtPlaceOther = reservationServrtPlaceOther;
    }
}
