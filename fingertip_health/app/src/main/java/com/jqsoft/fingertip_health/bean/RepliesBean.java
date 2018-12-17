package com.jqsoft.fingertip_health.bean;

/**
 * Created by Mars on 2018/3/30.
 */

public class RepliesBean {
    private String  isTurn;
    private  String replyContent;
    private String replyTime;
    private String replyUnit;
    private String  replyUnitCode;

    public RepliesBean() {
    }

    public RepliesBean(String isTurn, String replyContent, String replyTime, String replyUnit, String replyUnitCode) {
        this.isTurn = isTurn;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
        this.replyUnit = replyUnit;
        this.replyUnitCode = replyUnitCode;
    }

    public String getIsTurn() {
        return isTurn;
    }

    public void setIsTurn(String isTurn) {
        this.isTurn = isTurn;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyUnit() {
        return replyUnit;
    }

    public void setReplyUnit(String replyUnit) {
        this.replyUnit = replyUnit;
    }

    public String getReplyUnitCode() {
        return replyUnitCode;
    }

    public void setReplyUnitCode(String replyUnitCode) {
        this.replyUnitCode = replyUnitCode;
    }
}
