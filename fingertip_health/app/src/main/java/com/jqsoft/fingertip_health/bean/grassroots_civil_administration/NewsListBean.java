package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * Created by Mars on 2018/2/27.
 */

public class NewsListBean implements Serializable {
    private  String id;
    private String author;
    private String title;

    public NewsListBean(String id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public NewsListBean() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
