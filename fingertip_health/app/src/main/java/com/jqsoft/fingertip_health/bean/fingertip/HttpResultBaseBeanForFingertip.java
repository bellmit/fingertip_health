package com.jqsoft.fingertip_health.bean.fingertip;

/**
 * Created by Administrator on 2018-09-04.
 */

public class HttpResultBaseBeanForFingertip<T> {
    private String flag;
    private String errorMsg;
    private T result;
    public HttpResultBaseBeanForFingertip() {
        super();
    }

    public HttpResultBaseBeanForFingertip(String flag, String errorMsg, T result) {
        this.flag = flag;
        this.errorMsg = errorMsg;
        this.result = result;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
