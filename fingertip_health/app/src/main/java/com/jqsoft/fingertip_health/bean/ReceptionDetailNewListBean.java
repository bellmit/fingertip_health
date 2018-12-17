package com.jqsoft.fingertip_health.bean;

/**
 * Created by Mars on 2018/2/27.
 */

public class ReceptionDetailNewListBean {
    private String  author;
    private String id;
    private String message;
    private String title;
    private String subTitle;
    private String state;
    private  String releaseTime;
    private String releasePerson;
    private String receptionId;

    public ReceptionDetailNewListBean(String author, String id, String message, String title, String subTitle, String state, String releaseTime, String releasePerson, String receptionId) {
        this.author = author;
        this.id = id;
        this.message = message;
        this.title = title;
        this.subTitle = subTitle;
        this.state = state;
        this.releaseTime = releaseTime;
        this.releasePerson = releasePerson;
        this.receptionId = receptionId;
    }

    public ReceptionDetailNewListBean() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public String getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(String receptionId) {
        this.receptionId = receptionId;
    }
}
