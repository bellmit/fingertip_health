package com.jqsoft.fingertip_health.bean.fingertip;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-09-06.
 */

public class CurrentNotificationBeanForFingertip implements Serializable{
    private String id;//	主键
    private String author;//	作者名称
    private String message;//	文章内容
    private String releaseperson;//	发布人编码
    private String releasetime;//	发布时间
    private String title;//	文章标题
    private String subtitle;//	文章副标题

    public CurrentNotificationBeanForFingertip() {
        super();
    }

    public CurrentNotificationBeanForFingertip(String id, String author, String message, String releaseperson, String releasetime, String title, String subtitle) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.releaseperson = releaseperson;
        this.releasetime = releasetime;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReleaseperson() {
        return releaseperson;
    }

    public void setReleaseperson(String releaseperson) {
        this.releaseperson = releaseperson;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
