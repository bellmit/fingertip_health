package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/9/10.
 */

public class RegisterResultbean {
    //{"paientId":"156010665","visitNumber":"153656938301000000"}
    private String paientId;
    private String visitNumber;

    public RegisterResultbean(String paientId, String visitNumber) {
        this.paientId = paientId;
        this.visitNumber = visitNumber;
    }

    public RegisterResultbean() {
    }

    public String getPaientId() {
        return paientId;
    }

    public void setPaientId(String paientId) {
        this.paientId = paientId;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }
}
