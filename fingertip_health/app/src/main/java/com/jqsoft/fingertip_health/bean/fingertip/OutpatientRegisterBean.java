package com.jqsoft.fingertip_health.bean.fingertip;

/**
 * Created by Administrator on 2018/9/7.
 */

public class OutpatientRegisterBean {

    private String visitNumber;//	本次就诊号	N	String
    private String paientId;//病人ID	N	String

    public OutpatientRegisterBean() {
    }

    public OutpatientRegisterBean(String visitNumber, String paientId) {
        this.visitNumber = visitNumber;
        this.paientId = paientId;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getPaientId() {
        return paientId;
    }

    public void setPaientId(String paientId) {
        this.paientId = paientId;
    }
}
