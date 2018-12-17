package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * 低保审批情况趋势统计
 * Created by Administrator on 2018-01-03.
 */

public class SubsistenceApproveTrendStatisticsBean {
    private List<String> xTitle;
    private List<SubsistenceApproveTrendSpecificCategoryBean> list;
    public SubsistenceApproveTrendStatisticsBean() {
        super();
    }

    public SubsistenceApproveTrendStatisticsBean(List<String> xTitle, List<SubsistenceApproveTrendSpecificCategoryBean> list) {
        this.xTitle = xTitle;
        this.list = list;
    }

    public List<String> getxTitle() {
        return xTitle;
    }

    public void setxTitle(List<String> xTitle) {
        this.xTitle = xTitle;
    }

    public List<SubsistenceApproveTrendSpecificCategoryBean> getList() {
        return list;
    }

    public void setList(List<SubsistenceApproveTrendSpecificCategoryBean> list) {
        this.list = list;
    }
}
