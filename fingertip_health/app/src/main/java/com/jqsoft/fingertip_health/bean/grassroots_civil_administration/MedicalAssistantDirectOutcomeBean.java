package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 医疗救助-直接医疗救助支出情况（一站式）
 * Created by Administrator on 2018-01-10.
 */

public class MedicalAssistantDirectOutcomeBean {
    private String title;
    private String firstValue;//人次,救助金额同比增长率（%）,合规救助比例（%）
    private String secondValue;//金额,救助人次同比增长率（%）,次均救助金额（元）
    private String thirdValue;//救助金额环比增长率（%）
    private String fourthValue;//救助人次环比增长率（%）
    public MedicalAssistantDirectOutcomeBean() {
        super();
    }

    public MedicalAssistantDirectOutcomeBean(String title, String firstValue, String secondValue) {
        this.title = title;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public MedicalAssistantDirectOutcomeBean(String title, String firstValue, String secondValue, String thirdValue, String fourthValue) {
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
