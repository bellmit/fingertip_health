package com.jqsoft.fingertip_health.bean.fingertip;

import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.IStringRepresentationAndValue;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-08-16.
 * 签约居民端应用程序登录后返回的bean
 */

public class gdws_ph_dict_item extends DataSupport implements Serializable  {
    private String item_code;
    private String version_num;
    private String dict_code;
    private String name;
    private String gb_code;
    private String remark;
    private String status;
    private String update_time;
    private String updator;
    private String search_code;
    private String sort_no;


    public gdws_ph_dict_item() {
    }

    public gdws_ph_dict_item(String item_code, String version_num, String dict_code, String name, String gb_code, String remark, String status, String update_time, String updator, String search_code, String sort_no) {
        this.item_code = item_code;
        this.version_num = version_num;
        this.dict_code = dict_code;
        this.name = name;
        this.gb_code = gb_code;
        this.remark = remark;
        this.status = status;
        this.update_time = update_time;
        this.updator = updator;
        this.search_code = search_code;
        this.sort_no = sort_no;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getVersion_num() {
        return version_num;
    }

    public void setVersion_num(String version_num) {
        this.version_num = version_num;
    }

    public String getDict_code() {
        return dict_code;
    }

    public void setDict_code(String dict_code) {
        this.dict_code = dict_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGb_code() {
        return gb_code;
    }

    public void setGb_code(String gb_code) {
        this.gb_code = gb_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getSearch_code() {
        return search_code;
    }

    public void setSearch_code(String search_code) {
        this.search_code = search_code;
    }

    public String getSort_no() {
        return sort_no;
    }

    public void setSort_no(String sort_no) {
        this.sort_no = sort_no;
    }
}
