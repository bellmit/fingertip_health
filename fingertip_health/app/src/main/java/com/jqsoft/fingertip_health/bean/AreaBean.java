package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017/5/13.
 */

public class AreaBean {
    private String name;
    private String code;//代码
    private int id;

    public AreaBean() {
    }

    public AreaBean(String name, String code, int id) {
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
