package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * 低保变化情况趋势统计
 * Created by Administrator on 2018-01-03.
 */

public class SubsistenceVarianceTrendStatisticsBean {
    private List<String> xTitle;
    private List<SubsistenceVarianceTrendSpecificCategoryBean> list;
    public SubsistenceVarianceTrendStatisticsBean() {
        super();
    }

    public SubsistenceVarianceTrendStatisticsBean(List<String> xTitle, List<SubsistenceVarianceTrendSpecificCategoryBean> list) {
        this.xTitle = xTitle;
        this.list = list;
    }

    public List<String> getxTitle() {
        return xTitle;
    }

    public void setxTitle(List<String> xTitle) {
        this.xTitle = xTitle;
    }

    public List<SubsistenceVarianceTrendSpecificCategoryBean> getList() {
        return list;
    }

    public void setList(List<SubsistenceVarianceTrendSpecificCategoryBean> list) {
        this.list = list;
    }
}
