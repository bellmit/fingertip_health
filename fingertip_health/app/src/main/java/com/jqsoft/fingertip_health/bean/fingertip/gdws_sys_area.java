package com.jqsoft.fingertip_health.bean.fingertip;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.IStringRepresentationAndValue;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-08-16.
 * 签约居民端应用程序登录后返回的bean
 */

public class gdws_sys_area extends DataSupport implements Serializable , IStringRepresentationAndValue {
    private String code;
    private String p_code;
    private String name;
    private String area_level;


    public gdws_sys_area() {
    }

    public gdws_sys_area(String code, String p_code, String name, String area_level) {
        this.code = code;
        this.p_code = p_code;
        this.name = name;
        this.area_level = area_level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea_level() {
        return area_level;
    }

    public void setArea_level(String area_level) {
        this.area_level = area_level;
    }

    @Override
    public String getStringRepresentation() {
        return name;
    }

    @Override
    public String getStringValue() {
        return code;
    }
}
