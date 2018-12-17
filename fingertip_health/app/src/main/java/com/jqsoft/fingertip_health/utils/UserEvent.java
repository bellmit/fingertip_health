package com.jqsoft.fingertip_health.utils;

/**
 * Created by Administrator on 2017-05-12.
 */

public class UserEvent {
    private String userName;

    public UserEvent() {
    }

    public UserEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
