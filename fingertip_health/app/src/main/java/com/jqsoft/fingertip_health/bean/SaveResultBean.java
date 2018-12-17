package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-05-18.
 */

public class SaveResultBean {
    private String success;
    private String message;


    public SaveResultBean() {
    }

    public SaveResultBean(String success, String msg) {

        this.success = success;
        this.message = msg;

    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
