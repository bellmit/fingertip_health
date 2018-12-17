package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-26.
 */

public class TownLevelMedicalInstitutionDirectoryResultBean {
    private String id;
    private String name;
    private String code;    //编码
    private String numberOfVillageLevelInstitution; //村级医疗机构个数
    public TownLevelMedicalInstitutionDirectoryResultBean() {
        super();
    }

    public TownLevelMedicalInstitutionDirectoryResultBean(String id, String name, String code, String numberOfVillageLevelInstitution) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.numberOfVillageLevelInstitution = numberOfVillageLevelInstitution;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumberOfVillageLevelInstitution() {
        return numberOfVillageLevelInstitution;
    }

    public void setNumberOfVillageLevelInstitution(String numberOfVillageLevelInstitution) {
        this.numberOfVillageLevelInstitution = numberOfVillageLevelInstitution;
    }
}
