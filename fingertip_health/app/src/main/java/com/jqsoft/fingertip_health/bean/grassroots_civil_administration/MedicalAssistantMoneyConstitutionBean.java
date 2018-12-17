package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * （一站式）
 * 医疗救助资金构成
 医疗救助资金补偿方式构成
 医疗救助资金补偿类型构成
 * Created by Administrator on 2018-01-10.
 */

public class MedicalAssistantMoneyConstitutionBean {
    private String name;
    private String value;
    public MedicalAssistantMoneyConstitutionBean() {
        super();
    }

    public MedicalAssistantMoneyConstitutionBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
