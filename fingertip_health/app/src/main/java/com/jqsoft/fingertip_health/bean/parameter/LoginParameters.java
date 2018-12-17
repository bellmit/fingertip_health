package com.jqsoft.fingertip_health.bean.parameter;


import com.jqsoft.fingertip_health.annotation.HttpParameter;
import com.jqsoft.fingertip_health.bean.parameter.base.CommonParameters;

/**
 * Created by Administrator on 2017-05-18.
 */

public class LoginParameters extends CommonParameters {
    @HttpParameter
    private String username;
    @HttpParameter
    private String password;

    public LoginParameters(String key, String timestamp, String token, String sig, String v, String username, String password) {
        super(key, timestamp, token, sig, v);
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
