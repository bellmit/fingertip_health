package com.jqsoft.fingertip_health.bean.base;

/**
 * Created by Administrator on 2017-06-02.
 */

public class HttpRequestBaseBean {
    private String v;
    private String timestamp;
    private String token;
    private String appkey;
    private String sig;

    public HttpRequestBaseBean() {
    }

    public HttpRequestBaseBean(String v, String timestamp, String token, String appkey, String sig) {
        this.v = v;
        this.timestamp = timestamp;
        this.token = token;
        this.appkey = appkey;
        this.sig = sig;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
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

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }
}
