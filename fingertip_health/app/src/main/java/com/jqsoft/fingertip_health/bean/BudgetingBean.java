package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/12.
 */

public class BudgetingBean implements Serializable{


    private String feeTotal;//	总费用	N	Double
    private String percent;//报销比例	N	Integer	百分比（0~100）
    private String feePay;//自费金额	N	Double
    private String feeWestern;//西药费	N	Double
    private String feePatent;//成药费	N	Double
    private String feeHerbal;//草药费	N	Double
    private String feeMaterials;//	材料费	N	Double
    private String feeTreat;//	诊疗费	N	Double
    private CmsReimbursement cmsReimbursement;

    public BudgetingBean() {
    }

    public BudgetingBean(String feeTotal, String percent, String feePay, String feeWestern, String feePatent, String feeHerbal, String feeMaterials, String feeTreat, CmsReimbursement cmsReimbursement) {
        this.feeTotal = feeTotal;
        this.percent = percent;
        this.feePay = feePay;
        this.feeWestern = feeWestern;
        this.feePatent = feePatent;
        this.feeHerbal = feeHerbal;
        this.feeMaterials = feeMaterials;
        this.feeTreat = feeTreat;
        this.cmsReimbursement = cmsReimbursement;
    }

    public String getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(String feeTotal) {
        this.feeTotal = feeTotal;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getFeePay() {
        return feePay;
    }

    public void setFeePay(String feePay) {
        this.feePay = feePay;
    }

    public String getFeeWestern() {
        return feeWestern;
    }

    public void setFeeWestern(String feeWestern) {
        this.feeWestern = feeWestern;
    }

    public String getFeePatent() {
        return feePatent;
    }

    public void setFeePatent(String feePatent) {
        this.feePatent = feePatent;
    }

    public String getFeeHerbal() {
        return feeHerbal;
    }

    public void setFeeHerbal(String feeHerbal) {
        this.feeHerbal = feeHerbal;
    }

    public String getFeeMaterials() {
        return feeMaterials;
    }

    public void setFeeMaterials(String feeMaterials) {
        this.feeMaterials = feeMaterials;
    }

    public String getFeeTreat() {
        return feeTreat;
    }

    public void setFeeTreat(String feeTreat) {
        this.feeTreat = feeTreat;
    }

    public CmsReimbursement getCmsReimbursement() {
        return cmsReimbursement;
    }

    public void setCmsReimbursement(CmsReimbursement cmsReimbursement) {
        this.cmsReimbursement = cmsReimbursement;
    }
}



