package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 医疗救助-资助参保（参合）支出情况（一站式）
 * Created by Administrator on 2018-01-10.
 */

public class MedicalAssistantFinanceAssuranceBean {
    private String title;
    private String firstValue;//资助人数,资助参保金额同比增长率（%）
    private String secondValue;//资助金额（元）,资助参保人数同比增长率（%）
    private String thirdValue;//资助参保金额环比增长率（%）
    private String fourthValue;//资助参保人数环比增长率（%）
    public MedicalAssistantFinanceAssuranceBean() {
        super();
    }

    public MedicalAssistantFinanceAssuranceBean(String title, String firstValue, String secondValue) {
        this.title = title;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public MedicalAssistantFinanceAssuranceBean(String title, String firstValue, String secondValue, String thirdValue, String fourthValue) {
        this.title = title;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public String getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(String thirdValue) {
        this.thirdValue = thirdValue;
    }

    public String getFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(String fourthValue) {
        this.fourthValue = fourthValue;
    }
}
