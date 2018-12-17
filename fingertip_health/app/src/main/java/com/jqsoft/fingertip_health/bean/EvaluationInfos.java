package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/8/21.
 */

public class EvaluationInfos {
    private String docUserName;
    private String evaluation;
    private String evaluationNote;
    private String packageName;
    private String serverContentName;
    private String serverDT;
    private String servicePlanID;

    public EvaluationInfos() {

    }

    public EvaluationInfos(String docUserName, String evaluation, String evaluationNote, String packageName, String serverContentName, String serverDT, String servicePlanID) {
        this.docUserName = docUserName;
        this.evaluation = evaluation;
        this.evaluationNote = evaluationNote;
        this.packageName = packageName;
        this.serverContentName = serverContentName;
        this.serverDT = serverDT;
        this.servicePlanID = servicePlanID;
    }

    public String getDocUserName() {
        return docUserName;
    }

    public void setDocUserName(String docUserName) {
        this.docUserName = docUserName;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluationNote() {
        return evaluationNote;
    }

    public void setEvaluationNote(String evaluationNote) {
        this.evaluationNote = evaluationNote;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getServerContentName() {
        return serverContentName;
    }

    public void setServerContentName(String serverContentName) {
        this.serverContentName = serverContentName;
    }

    public String getServerDT() {
        return serverDT;
    }

    public void setServerDT(String serverDT) {
        this.serverDT = serverDT;
    }

    public String getServicePlanID() {
        return servicePlanID;
    }

    public void setServicePlanID(String servicePlanID) {
        this.servicePlanID = servicePlanID;
    }
}
