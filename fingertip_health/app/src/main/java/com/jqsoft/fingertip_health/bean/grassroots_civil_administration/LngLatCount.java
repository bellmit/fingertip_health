package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Administrator on 2018-02-02.
 */

public class LngLatCount {
    private String count;//该地图点的困难群众数量
    private String lng;//经度
    private String lat;//纬度
    public LngLatCount() {
        super();
    }

    public LngLatCount(String count, String lng, String lat) {
        this.count = count;
        this.lng = lng;
        this.lat = lat;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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
}
