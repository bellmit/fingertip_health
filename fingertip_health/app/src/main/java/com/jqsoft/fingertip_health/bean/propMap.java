package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Mars on 2018/9/11.
 */

public class propMap implements Serializable {
    private  String code;
    private  String color;
    private  String sname;
    private  String isMain;
    private  String name;

    public propMap(String code, String color, String sname, String isMain, String name) {
        this.code = code;
        this.color = color;
        this.sname = sname;
        this.isMain = isMain;
        this.name = name;
    }

    public propMap() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
