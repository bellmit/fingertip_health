package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * 民政指南
 * Created by Administrator on 2017-12-27.
 */

public class PolityBean implements Serializable {
    private String code;
    private String name;


    private String id;

    public PolityBean() {
        super();
    }

    public PolityBean(String code, String name,String id) {
        this.code = code;
        this.id=  id;
        this.name = name;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
