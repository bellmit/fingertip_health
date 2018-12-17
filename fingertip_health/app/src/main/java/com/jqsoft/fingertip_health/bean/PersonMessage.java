package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/8/31.
 */

public class PersonMessage {
    private String id;//                 在线咨询主键
    private String postMessage;// /           发送内容
    private String recipient;//             接收者（）
    private String recipientName;//             接收者名称
    private String sender;//              发送者
    private String senderName;//               发送者名称
    private String setTime;//              发送时间
    private String status;//   //                1 未读  2已读


    private String photoUrl;
    public PersonMessage() {
    }


    public PersonMessage(String id, String postMessage, String recipient, String recipientName, String sender, String senderName, String setTime, String status,String photoUrl) {
        this.id = id;
        this.postMessage = postMessage;
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.senderName = senderName;
        this.setTime = setTime;
        this.status = status;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
