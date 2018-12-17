package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * 低保审批情况趋势统计某一类别bean
 * Created by Administrator on 2018-01-03.
 */

public class SubsistenceApproveTrendSpecificCategoryBean {
    private String name;
    private List<String> list;
    public SubsistenceApproveTrendSpecificCategoryBean() {
        super();
    }

    public SubsistenceApproveTrendSpecificCategoryBean(String name, List<String> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
