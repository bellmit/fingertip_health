package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 临时（受灾）救助趋势分析，排名统计，救助水平（次均救助金额排名统计，次均救助金额趋势分析）
 * Created by Administrator on 2018-01-11.
 */

public class TempDisasterAssistantBean {
    private String title;
    private String firstValue;//救助金额，次均救助金额
    private String secondValue;//救助次数
    public TempDisasterAssistantBean() {
        super();
    }

    public TempDisasterAssistantBean(String title, String firstValue, String secondValue) {
        this.title = title;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
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
}
