package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * Created by Mars on 2018/1/8.
 */

public class GuideBean implements Serializable {
    private  String code;
    private  String name;
    private  String  pcode;


    public GuideBean() {
    }

    public GuideBean(String code, String name, String pcode) {
        this.code = code;
        this.name = name;
        this.pcode = pcode;
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

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
}
