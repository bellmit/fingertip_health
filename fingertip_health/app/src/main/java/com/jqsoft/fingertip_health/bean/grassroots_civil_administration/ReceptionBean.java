package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * 受理中心
 * Created by Administrator on 2017-12-27.
 */

public class ReceptionBean implements Serializable {

    private String areaCode;
    private String areaLevel;
    private String name;
    private String areaPid;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaPid() {
        return areaPid;
    }

    public void setAreaPid(String areaPid) {
        this.areaPid = areaPid;
    }



    public ReceptionBean() {
        super();
    }

    public ReceptionBean(String areaCode, String areaLevel, String name,String areaPid) {
        this.areaCode = areaCode;
        this.areaLevel=  areaLevel;
        this.name = name;
        this.areaPid = areaPid;

    }


}
