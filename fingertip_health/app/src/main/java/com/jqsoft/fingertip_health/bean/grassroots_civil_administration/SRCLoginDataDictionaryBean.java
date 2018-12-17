package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import com.bigkoo.pickerview.model.IPickerViewData;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-08-16.
 * 签约居民端应用程序登录后返回的bean
 */

public class SRCLoginDataDictionaryBean extends DataSupport implements Serializable, IPickerViewData {
    private String code;
    private String name;
    private String pCode;
    private String sort;
    private String state;
    private String lastTime;


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


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }
}
