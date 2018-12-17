package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

/**
 * 基层民政工作平台服务返回的http base bean
 * Created by Administrator on 2017-12-27.
 */

public class GCAHttpResultHuifuBaseBean<T> {
    private String result;//1成功，0失败
    private String msg;
    private T data;
    private String type;
    public GCAHttpResultHuifuBaseBean() {
        super();
    }

    public GCAHttpResultHuifuBaseBean(String result, String msg, T data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public GCAHttpResultHuifuBaseBean(String result, String msg, T data, String type) {
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
