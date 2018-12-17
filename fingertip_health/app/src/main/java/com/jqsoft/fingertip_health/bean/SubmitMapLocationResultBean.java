package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018-02-06.
 */

public class SubmitMapLocationResultBean {
    private String data;
    private String msg;
    private String result;

    public SubmitMapLocationResultBean() {
    }

    public SubmitMapLocationResultBean(String data, String msg, String result) {
        this.data = data;
        this.msg = msg;
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
