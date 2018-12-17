package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * 通知公告
 * Created by Administrator on 2017-12-27.
 */

public class NotificationBean implements Serializable {
    private String id;
    private String releaseTime;
    private String title;
    private String author;
    private String message;
    private String messageFirstImgSrc;
    private String picture;
    private String type;
    private String areaLevel;

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public NotificationBean() {
        super();
    }

    public NotificationBean(String areaLevel,String id, String releaseTime, String title, String author, String message, String messageFirstImgSrc, String picture, String type) {
        this.id = id;
        this.releaseTime = releaseTime;
        this.title = title;
        this.author = author;
        this.message = message;
        this.messageFirstImgSrc = messageFirstImgSrc;
        this.picture = picture;
        this.type = type;
        this.areaLevel=areaLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMessageFirstImgSrc() {
        return messageFirstImgSrc;
    }

    public void setMessageFirstImgSrc(String messageFirstImgSrc) {
        this.messageFirstImgSrc = messageFirstImgSrc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
