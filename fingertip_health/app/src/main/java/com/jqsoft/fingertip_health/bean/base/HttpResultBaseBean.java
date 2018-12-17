package com.jqsoft.fingertip_health.bean.base;

/**
 * Created by Administrator on 2017-05-18.
 */

public class HttpResultBaseBean<T> {
    private String result;
    private String msg;
    private T data;

    public HttpResultBaseBean() {
    }

    public HttpResultBaseBean(String result, String msg, T data) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
