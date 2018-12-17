package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Mars on 2018/3/28.
 */

public class QuestionDetailBean {
    private  String title;
    private  String releaseTime;
    private  String id;
    private  String content;

    public QuestionDetailBean() {
    }

    public QuestionDetailBean(String title, String releaseTime, String id, String content) {
        this.title = title;
        this.releaseTime = releaseTime;
        this.id = id;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
