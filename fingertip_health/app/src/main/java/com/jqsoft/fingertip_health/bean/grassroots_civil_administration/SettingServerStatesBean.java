package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Mars on 2018/2/7.
 */

public class SettingServerStatesBean {
    private String  ip;
    private  boolean ischeck;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public SettingServerStatesBean() {
    }

    public SettingServerStatesBean(String ip, boolean ischeck) {
        this.ip = ip;
        this.ischeck = ischeck;
    }
}
