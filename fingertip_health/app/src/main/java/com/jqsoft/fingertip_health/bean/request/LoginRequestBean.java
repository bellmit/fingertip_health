package com.jqsoft.fingertip_health.bean.request;

import com.jqsoft.fingertip_health.bean.base.HttpRequestBaseBean;

/**
 * Created by Administrator on 2017-06-01.
 */

public class LoginRequestBean extends HttpRequestBaseBean {
    private String username;
    private String password;

    public LoginRequestBean() {
    }

    public LoginRequestBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequestBean(String v, String timestamp, String token, String appkey, String sig, String username, String password) {
        super(v, timestamp, token, appkey, sig);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
