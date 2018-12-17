package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/3/27.
 */

public class LocationMapBean {
    private String  id;
    private  String lng;
    private String lat;
    private  String address;
    private  String batchNo;

    public LocationMapBean(String id, String lng, String lat, String address, String batchNo) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.address = address;
        this.batchNo = batchNo;
    }

    public LocationMapBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}
