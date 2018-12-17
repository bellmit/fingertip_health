package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/12.
 */

public class CmsReimbursement implements Serializable{

    private double totalMoney;//总费用	N	Double
    private double startMoney;//起付线	N	Double
    private double enableMoney;//保内费用	N	Double
    private double medicineMoney;//	目录补偿	N	Double
    private double generalMoney;//	统筹补偿	N	Double
    private double bottomMoney;//	兜底补偿	N	Double
    private double civilPreferential;//	民政优抚补助	N	Double
    private double civilAssistance;//	民政城乡救助	N	Double
    private double familyAccountMoney;//家庭账户补偿	N	Double
    private double CDPFMoney;//残联补偿	N	Double
    private double cmsMoney;//农合补偿费用	N	Double
    private double familyPlanningMoney;//	计划生育救助费用	N	Double
    private double aidMoney;//	扶贫办补偿	N	Double
    private double personalPayMoney;//个人支付	N	Double
    private double CIIMoney;//大病保险	N	Double
    private double CDCMoney;//疾控中心补偿	N	Double
    private double materialMoney;//高额材料限价超额费用	N	Double
    private double otherMoney;//其他补偿金额	N	Double

//    总费用   包内费用
//    农合补偿 费用   统筹支付
//    家庭账户支付     个人支付
    public CmsReimbursement() {
    }

    public CmsReimbursement(double totalMoney, double startMoney, double enableMoney, double medicineMoney, double generalMoney, double bottomMoney, double civilPreferential, double civilAssistance, double familyAccountMoney, double CDPFMoney, double cmsMoney, double familyPlanningMoney, double aidMoney, double personalPayMoney, double CIIMoney, double CDCMoney, double materialMoney, double otherMoney) {
        this.totalMoney = totalMoney;
        this.startMoney = startMoney;
        this.enableMoney = enableMoney;
        this.medicineMoney = medicineMoney;
        this.generalMoney = generalMoney;
        this.bottomMoney = bottomMoney;
        this.civilPreferential = civilPreferential;
        this.civilAssistance = civilAssistance;
        this.familyAccountMoney = familyAccountMoney;
        this.CDPFMoney = CDPFMoney;
        this.cmsMoney = cmsMoney;
        this.familyPlanningMoney = familyPlanningMoney;
        this.aidMoney = aidMoney;
        this.personalPayMoney = personalPayMoney;
        this.CIIMoney = CIIMoney;
        this.CDCMoney = CDCMoney;
        this.materialMoney = materialMoney;
        this.otherMoney = otherMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(double startMoney) {
        this.startMoney = startMoney;
    }

    public double getEnableMoney() {
        return enableMoney;
    }

    public void setEnableMoney(double enableMoney) {
        this.enableMoney = enableMoney;
    }

    public double getMedicineMoney() {
        return medicineMoney;
    }

    public void setMedicineMoney(double medicineMoney) {
        this.medicineMoney = medicineMoney;
    }

    public double getGeneralMoney() {
        return generalMoney;
    }

    public void setGeneralMoney(double generalMoney) {
        this.generalMoney = generalMoney;
    }

    public double getBottomMoney() {
        return bottomMoney;
    }

    public void setBottomMoney(double bottomMoney) {
        this.bottomMoney = bottomMoney;
    }

    public double getCivilPreferential() {
        return civilPreferential;
    }

    public void setCivilPreferential(double civilPreferential) {
        this.civilPreferential = civilPreferential;
    }

    public double getCivilAssistance() {
        return civilAssistance;
    }

    public void setCivilAssistance(double civilAssistance) {
        this.civilAssistance = civilAssistance;
    }

    public double getFamilyAccountMoney() {
        return familyAccountMoney;
    }

    public void setFamilyAccountMoney(double familyAccountMoney) {
        this.familyAccountMoney = familyAccountMoney;
    }

    public double getCDPFMoney() {
        return CDPFMoney;
    }

    public void setCDPFMoney(double CDPFMoney) {
        this.CDPFMoney = CDPFMoney;
    }

    public double getCmsMoney() {
        return cmsMoney;
    }

    public void setCmsMoney(double cmsMoney) {
        this.cmsMoney = cmsMoney;
    }

    public double getFamilyPlanningMoney() {
        return familyPlanningMoney;
    }

    public void setFamilyPlanningMoney(double familyPlanningMoney) {
        this.familyPlanningMoney = familyPlanningMoney;
    }

    public double getAidMoney() {
        return aidMoney;
    }

    public void setAidMoney(double aidMoney) {
        this.aidMoney = aidMoney;
    }

    public double getPersonalPayMoney() {
        return personalPayMoney;
    }

    public void setPersonalPayMoney(double personalPayMoney) {
        this.personalPayMoney = personalPayMoney;
    }

    public double getCIIMoney() {
        return CIIMoney;
    }

    public void setCIIMoney(double CIIMoney) {
        this.CIIMoney = CIIMoney;
    }

    public double getCDCMoney() {
        return CDCMoney;
    }

    public void setCDCMoney(double CDCMoney) {
        this.CDCMoney = CDCMoney;
    }

    public double getMaterialMoney() {
        return materialMoney;
    }

    public void setMaterialMoney(double materialMoney) {
        this.materialMoney = materialMoney;
    }

    public double getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(double otherMoney) {
        this.otherMoney = otherMoney;
    }
}



