package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/2/2.
 */

public class GCAHttpResultBaseObjectBean<T> {
    private String result;//1成功，0失败
    private String msg;
    private Object data;

    public GCAHttpResultBaseObjectBean() {
        super();
    }

    public GCAHttpResultBaseObjectBean(String result, String msg, Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
