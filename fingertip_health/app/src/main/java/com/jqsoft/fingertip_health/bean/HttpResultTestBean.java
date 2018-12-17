package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/2/1.
 */

public class HttpResultTestBean {
    private String result;
    private String msg;
    private String data;

    public HttpResultTestBean() {
    }

    public HttpResultTestBean(String result, String msg, String data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public String getSuccess() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
