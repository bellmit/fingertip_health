package com.jqsoft.fingertip_health.bean;

/**
 * Created by Mars on 2018/3/28.
 */

public class AdviceBean {
    private  String title;
    private  String isReply;
    private  String id;
    private  String replyContent;
    private  String conNo;
    private  String content;
    private String replyTime;

    public AdviceBean(String title, String isReply, String id, String replyContent, String conNo, String content, String replyTime) {
        this.title = title;
        this.isReply = isReply;
        this.id = id;
        this.replyContent = replyContent;
        this.conNo = conNo;
        this.content = content;
        this.replyTime = replyTime;
    }

    public AdviceBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsReply() {
        return isReply;
    }

    public void setIsReply(String isReply) {
        this.isReply = isReply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
