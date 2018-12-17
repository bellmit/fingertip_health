package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Mars on 2018/2/6.
 */

public class SettingServerBean extends DataSupport implements Serializable {
    private String ip;
    private String address;
    private String username;
    private String isUse;


    public SettingServerBean(String ip, String address, String username, String isUse) {
        this.ip = ip;
        this.address = address;
        this.username = username;
        this.isUse = isUse;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }
}
