package com.jqsoft.fingertip_health.bean;

/**
 * Created by Mars on 2018/3/27.
 */

public class QuestionBean {
    private String title;
    private  String id;

    public QuestionBean() {
    }

    public QuestionBean(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
