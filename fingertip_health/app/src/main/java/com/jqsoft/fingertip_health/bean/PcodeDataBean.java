package com.jqsoft.fingertip_health.bean;


import com.bigkoo.pickerview.model.IPickerViewData;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/12.
 */

public class PcodeDataBean extends DataSupport implements Serializable, IPickerViewData {
    private String id;
    private String code;
    private String pcode;
    private String configvalue;
    private String description;
    private String state;

    public PcodeDataBean() {
    }


    public PcodeDataBean(String id, String code, String pcode, String configvalue, String description, String state) {
        this.id = id;
        this.code = code;
        this.pcode = pcode;
        this.configvalue = configvalue;
        this.description = description;
        this.state = state;
    }

    @Override
    public String getPickerViewText() {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getConfigvalue() {
        return configvalue;
    }

    public void setConfigvalue(String configvalue) {
        this.configvalue = configvalue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
