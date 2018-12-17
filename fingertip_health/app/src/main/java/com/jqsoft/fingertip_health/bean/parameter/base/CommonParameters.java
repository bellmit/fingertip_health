package com.jqsoft.fingertip_health.bean.parameter.base;


import com.jqsoft.fingertip_health.annotation.HttpParameter;

/**
 * Created by Administrator on 2017-05-17.
 */

public class CommonParameters {
    @HttpParameter
    private String appkey;   //公钥
    @HttpParameter
    private String timestamp; //当前时间（时间戳）
    @HttpParameter
    private String token;
    @HttpParameter
    private String sig;//加密串
    @HttpParameter
    private String v;    //版本号

    public CommonParameters() {
    }

    public CommonParameters(String appkey, String timestamp, String token, String sig, String v) {
        this.appkey = appkey;
        this.timestamp = timestamp;
        this.token = token;
        this.sig = sig;
        this.v = v;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
