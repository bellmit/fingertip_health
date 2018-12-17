package com.jqsoft.fingertip_health.bean;

/**
 * Created by Mars on 2018/3/26.
 */

public class MyMessageDetailBean {

    private  String  acceptRealName;
    private String  addressee;
    private  String addresser;
    private  String  id;
    private  String  message;
    private  String  receiveEndTime;
    private  String  receiveStartTime;
    private  String  receiveTime;
    private  String  sendRealName;
    private  String  sendTime;
    private  String    state;
    private  String  theme;

    public MyMessageDetailBean() {
    }

    public MyMessageDetailBean(String acceptRealName, String addressee, String addresser, String id, String message, String receiveEndTime, String receiveStartTime, String receiveTime, String sendRealName, String sendTime, String state, String theme) {
        this.acceptRealName = acceptRealName;
        this.addressee = addressee;
        this.addresser = addresser;
        this.id = id;
        this.message = message;
        this.receiveEndTime = receiveEndTime;
        this.receiveStartTime = receiveStartTime;
        this.receiveTime = receiveTime;
        this.sendRealName = sendRealName;
        this.sendTime = sendTime;
        this.state = state;
        this.theme = theme;
    }

    public String getAcceptRealName() {
        return acceptRealName;
    }

    public void setAcceptRealName(String acceptRealName) {
        this.acceptRealName = acceptRealName;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getAddresser() {
        return addresser;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiveEndTime() {
        return receiveEndTime;
    }

    public void setReceiveEndTime(String receiveEndTime) {
        this.receiveEndTime = receiveEndTime;
    }

    public String getReceiveStartTime() {
        return receiveStartTime;
    }

    public void setReceiveStartTime(String receiveStartTime) {
        this.receiveStartTime = receiveStartTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getSendRealName() {
        return sendRealName;
    }

    public void setSendRealName(String sendRealName) {
        this.sendRealName = sendRealName;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
