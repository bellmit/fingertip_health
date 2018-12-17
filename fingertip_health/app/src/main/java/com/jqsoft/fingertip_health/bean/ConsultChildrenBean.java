package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Mars on 2018/3/30.
 */

public class ConsultChildrenBean {


        private String replyContent;
    private String conNo;
    private String content;
    private String consultTime;
    private List<RepliesBean> replis;
    private String replyTime;
    private String replyUnit;
    private String replyUnitCode;

    public ConsultChildrenBean() {
    }

    public ConsultChildrenBean(String replyContent, String conNo, String content, String consultTime, List<RepliesBean> replis, String replyTime, String replyUnit, String replyUnitCode) {
        this.replyContent = replyContent;
        this.conNo = conNo;
        this.content = content;
        this.consultTime = consultTime;
        this.replis = replis;
        this.replyTime = replyTime;
        this.replyUnit = replyUnit;
        this.replyUnitCode = replyUnitCode;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(String consultTime) {
        this.consultTime = consultTime;
    }

    public List<RepliesBean> getReplis() {
        return replis;
    }

    public void setReplis(List<RepliesBean> replis) {
        this.replis = replis;
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
