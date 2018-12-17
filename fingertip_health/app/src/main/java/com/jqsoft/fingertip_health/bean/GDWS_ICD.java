package com.jqsoft.fingertip_health.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/6.
 */

public class GDWS_ICD extends DataSupport implements Serializable{
//    //    @Column(unique = true)
    private String id;

    private String area_id;
    private String code;
    private String diagnostic_type;
    private String flag;
    private String name;
    private String pinyin;
    private String sortby;
    private String status;
    private String type;
    private String version_id;

    public GDWS_ICD() {
    }

    public GDWS_ICD(String id, String area_id, String code, String diagnostic_type, String flag, String name, String pinyin, String sortby, String status, String type, String version_id) {
        this.id = id;
        this.area_id = area_id;
        this.code = code;
        this.diagnostic_type = diagnostic_type;
        this.flag = flag;
        this.name = name;
        this.pinyin = pinyin;
        this.sortby = sortby;
        this.status = status;
        this.type = type;
        this.version_id = version_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSortby() {
        return sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion_id() {
        return version_id;
    }

    public void setVersion_id(String version_id) {
        this.version_id = version_id;
    }

    public String getDiagnostic_type() {
        return diagnostic_type;
    }

    public void setDiagnostic_type(String diagnostic_type) {
        this.diagnostic_type = diagnostic_type;
    }
}
