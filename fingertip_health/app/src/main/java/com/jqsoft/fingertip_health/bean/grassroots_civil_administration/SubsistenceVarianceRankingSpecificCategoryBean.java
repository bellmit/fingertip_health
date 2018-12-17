package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * 低保变化情况排名统计某一类别bean
 * Created by Administrator on 2018-01-03.
 */

public class SubsistenceVarianceRankingSpecificCategoryBean {
    private String name;
    private List<String> list;
    public SubsistenceVarianceRankingSpecificCategoryBean() {
        super();
    }

    public SubsistenceVarianceRankingSpecificCategoryBean(String name, List<String> list) {
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
