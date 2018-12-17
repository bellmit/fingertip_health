package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017/5/13.
 */

public class FunctionImageBean {
    private String title;
    private String id;
    private int imageResourceId;

    public FunctionImageBean(String title, String id, int imageResourceId) {
        this.title = title;
        this.id = id;
        this.imageResourceId = imageResourceId;
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

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
