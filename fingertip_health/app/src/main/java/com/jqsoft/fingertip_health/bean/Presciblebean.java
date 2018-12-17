package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/11.
 */

public class Presciblebean implements Serializable {
    private String visitNumber	;//本次就诊号	N	String
    private String prescriptionId;//	处方编号	N	String

    public Presciblebean() {
    }

    public Presciblebean(String visitNumber, String prescriptionId) {
        this.visitNumber = visitNumber;
        this.prescriptionId = prescriptionId;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
