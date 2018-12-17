package com.jqsoft.fingertip_health.bean.fingertip;

/**
 * Created by Administrator on 2018-09-06.
 */

public class OutpatientForFingertip {
    private String amountOutpatient;//	本日门诊人次
    private String feeAverage;//	本日均次奋勇
    private String feeCms;//	本日农合报销
    private String feeTotal;//	本日总费用
    private String ratioMedicine;//	本日药占比
    private String monthAmountOutpatient;//	本月门诊人次
    private String monthFeeAverage;//	本月均次费用
    private String monthFeeCms;//	本月农合报销
    private String monthFeeTotal;//	本月总费用
    private String monthRatioMedicine;//	本月药占比率

    public OutpatientForFingertip() {
        super();
    }

    public OutpatientForFingertip(String amountOutpatient, String feeAverage, String feeCms, String feeTotal, String ratioMedicine, String monthAmountOutpatient, String monthFeeAverage, String monthFeeCms, String monthFeeTotal, String monthRatioMedicine) {
        this.amountOutpatient = amountOutpatient;
        this.feeAverage = feeAverage;
        this.feeCms = feeCms;
        this.feeTotal = feeTotal;
        this.ratioMedicine = ratioMedicine;
        this.monthAmountOutpatient = monthAmountOutpatient;
        this.monthFeeAverage = monthFeeAverage;
        this.monthFeeCms = monthFeeCms;
        this.monthFeeTotal = monthFeeTotal;
        this.monthRatioMedicine = monthRatioMedicine;
    }

    public String getAmountOutpatient() {
        return amountOutpatient;
    }

    public void setAmountOutpatient(String amountOutpatient) {
        this.amountOutpatient = amountOutpatient;
    }

    public String getFeeAverage() {
        return feeAverage;
    }

    public void setFeeAverage(String feeAverage) {
        this.feeAverage = feeAverage;
    }

    public String getFeeCms() {
        return feeCms;
    }

    public void setFeeCms(String feeCms) {
        this.feeCms = feeCms;
    }

    public String getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(String feeTotal) {
        this.feeTotal = feeTotal;
    }

    public String getRatioMedicine() {
        return ratioMedicine;
    }

    public void setRatioMedicine(String ratioMedicine) {
        this.ratioMedicine = ratioMedicine;
    }

    public String getMonthAmountOutpatient() {
        return monthAmountOutpatient;
    }

    public void setMonthAmountOutpatient(String monthAmountOutpatient) {
        this.monthAmountOutpatient = monthAmountOutpatient;
    }

    public String getMonthFeeAverage() {
        return monthFeeAverage;
    }

    public void setMonthFeeAverage(String monthFeeAverage) {
        this.monthFeeAverage = monthFeeAverage;
    }

    public String getMonthFeeCms() {
        return monthFeeCms;
    }

    public void setMonthFeeCms(String monthFeeCms) {
        this.monthFeeCms = monthFeeCms;
    }

    public String getMonthFeeTotal() {
        return monthFeeTotal;
    }

    public void setMonthFeeTotal(String monthFeeTotal) {
        this.monthFeeTotal = monthFeeTotal;
    }

    public String getMonthRatioMedicine() {
        return monthRatioMedicine;
    }

    public void setMonthRatioMedicine(String monthRatioMedicine) {
        this.monthRatioMedicine = monthRatioMedicine;
    }
}
