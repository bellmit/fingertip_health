package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-01-19.
 */

public class PersonLocationBean implements Serializable {
    private String name ;// 姓名
    private String batchNo ;//批次编号
    private String phone ;// 联系电话
    private String address ;//地址
    private String familyType ;//家庭类别 （数据字典）
    private String lng ;//经度
    private String lat ;//纬度

    private String familyTypeReadable;//家庭类别（从数据字典里查出来的中文表达）

    private String type;//type= total时表示返回的街道及以上区划的分类汇总信息
//    type=point时表示返回的是街道、社区的分类详细信息

    public PersonLocationBean() {
        super();
    }

    public PersonLocationBean(String name, String batchNo, String phone, String address, String familyType, String lng, String lat, String familyTypeReadable, String type) {
        this.name = name;
        this.batchNo = batchNo;
        this.phone = phone;
        this.address = address;
        this.familyType = familyType;
        this.lng = lng;
        this.lat = lat;
        this.familyTypeReadable = familyTypeReadable;
        this.type = type;
    }

    public PersonLocationBean(String name, String batchNo, String phone, String address, String familyType, String lng, String lat) {
        this.name = name;
        this.batchNo = batchNo;
        this.phone = phone;
        this.address = address;
        this.familyType = familyType;
        this.lng = lng;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFamilyType() {
        return familyType;
    }

    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getFamilyTypeReadable() {
        return familyTypeReadable;
    }

    public void setFamilyTypeReadable(String familyTypeReadable) {
        this.familyTypeReadable = familyTypeReadable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
