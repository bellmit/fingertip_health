package com.jqsoft.fingertip_health.bean.response_new;

/**
 * Created by Administrator on 2017-07-11.
 */
//预约挂号中医院信息
public class AppointmentRegistrationResultBean {
    private String id;
    private String url;
    private String name;
    private String level;
    private String appointmentNumber;
    private String longitude;
    private String latitude;
    public AppointmentRegistrationResultBean() {
        super();
    }

    public AppointmentRegistrationResultBean(String id, String url, String name, String level, String appointmentNumber, String longitude, String latitude) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.level = level;
        this.appointmentNumber = appointmentNumber;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
