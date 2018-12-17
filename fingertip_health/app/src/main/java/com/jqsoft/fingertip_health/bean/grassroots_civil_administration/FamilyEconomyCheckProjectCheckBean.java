package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * 核对项目类统计
 * Created by Administrator on 2018-01-15.
 */

public class FamilyEconomyCheckProjectCheckBean {
    private String name;
    private List<NameValueBean> list;
    public FamilyEconomyCheckProjectCheckBean() {
        super();
    }

    public FamilyEconomyCheckProjectCheckBean(String name, List<NameValueBean> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NameValueBean> getList() {
        return list;
    }

    public void setList(List<NameValueBean> list) {
        this.list = list;
    }
}
