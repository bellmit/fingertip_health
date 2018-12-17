package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi;

/**
 * 街道及以上区划的分类汇总信息或被救助人信息
 * Created by Administrator on 2018-04-25.
 */

public class PersonStreetOrAboveLocationBean extends PersonLocationBean {
    private String areaCode;
    private String areaName;
    private String total;

    public PersonStreetOrAboveLocationBean() {
        super();
    }

    public PersonStreetOrAboveLocationBean(String areaCode, String areaName, String total, String lng, String lat) {
        super();
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.total = total;
        setLng(lng);
        setLat(lat);
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
