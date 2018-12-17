package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

/**
 * 基层民政工作平台服务返回的http base bean
 * Created by Administrator on 2017-12-27.
 */

public class GCAHttpResultBaseBean<T> {
    private String result;//1成功，0失败
    private String msg;
    private T data;
    public GCAHttpResultBaseBean() {
        super();
    }

    public GCAHttpResultBaseBean(String result, String msg, T data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
