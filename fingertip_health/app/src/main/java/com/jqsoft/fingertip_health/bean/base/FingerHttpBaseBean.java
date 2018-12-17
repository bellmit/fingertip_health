package com.jqsoft.fingertip_health.bean.base;


public class FingerHttpBaseBean<T> {
    private String flag;
    private T result;

    public FingerHttpBaseBean() {
    }

    public FingerHttpBaseBean(String flag, T result) {
        this.flag = flag;
        this.result = result;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
