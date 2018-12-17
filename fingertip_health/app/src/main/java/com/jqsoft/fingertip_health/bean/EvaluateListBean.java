package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/8/23.
 */

public class EvaluateListBean {
    private String serviceContentKey;//      服务内容主键
    private String serviceContentItemsKey;//      服务项目内容主键
    private String serviceItemsName;//        服务项目内容名称
    private String execOffer;//            执行单位
    private String signDetailID;//               服务明细主键
    private String shouldExecTimes;//             应该执行次数
    private String hadExecTimes;//             执行次数
    private String startExecData;//              开始执行时间
    private String endExecData;//             结束执行时间
    private String Finished;//               是否执行完（ 0：执行完，1：没有执行完）
    private String servicePlanID;//            项目执行情况主键
    private String doctorCode;//           签约医生账号
    private String orgID;//             机构主键
    private String docUserID;//             签约医生主键
    private String evaluationState;//               评价状态

    //  (null 没有评价 不是null 有评价)
    public EvaluateListBean() {
    }

    public EvaluateListBean(String serviceContentKey, String serviceContentItemsKey, String serviceItemsName, String execOffer, String signDetailID, String shouldExecTimes, String hadExecTimes, String startExecData, String endExecData, String finished, String servicePlanID, String doctorCode, String orgID, String docUserID, String evaluationState) {
        this.serviceContentKey = serviceContentKey;
        this.serviceContentItemsKey = serviceContentItemsKey;
        this.serviceItemsName = serviceItemsName;
        this.execOffer = execOffer;
        this.signDetailID = signDetailID;
        this.shouldExecTimes = shouldExecTimes;
        this.hadExecTimes = hadExecTimes;
        this.startExecData = startExecData;
        this.endExecData = endExecData;
        this.Finished = finished;
        this.servicePlanID = servicePlanID;
        this.doctorCode = doctorCode;
        this.orgID = orgID;
        this.docUserID = docUserID;
        this.evaluationState = evaluationState;
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

    public String getExecOffer() {
        return execOffer;
    }

    public void setExecOffer(String execOffer) {
        this.execOffer = execOffer;
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
        return Finished;
    }

    public void setFinished(String finished) {
        Finished = finished;
    }

    public String getServicePlanID() {
        return servicePlanID;
    }

    public void setServicePlanID(String servicePlanID) {
        this.servicePlanID = servicePlanID;
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


}
