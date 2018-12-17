package com.jqsoft.fingertip_health.bean.fingertip;

/**
 * Created by Administrator on 2018-09-05.
 */

public class DoctorBeanForFingertip {
    private String username;//	医生代码
    private String realName;//	医生姓名

    public DoctorBeanForFingertip() {
        super();
    }

    public DoctorBeanForFingertip(String username, String realName) {
        this.username = username;
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
