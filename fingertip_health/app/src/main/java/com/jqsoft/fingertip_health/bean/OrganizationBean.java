package com.jqsoft.fingertip_health.bean;


import com.bigkoo.pickerview.model.IPickerViewData;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/12.
 */

public class OrganizationBean extends DataSupport implements Serializable, IPickerViewData {
    private String aliasName;
    private String areaId;
    private String code;
    private String name;
    private String parentCode;
    private String unitType;
    private String gglx_xtbldw;

    private String state;
    private String lastTime;





    @Override
    public String getPickerViewText() {
        return name;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getGglx_xtbldw() {
        return gglx_xtbldw;
    }

    public void setGglx_xtbldw(String gglx_xtbldw) {
        this.gglx_xtbldw = gglx_xtbldw;
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
}
