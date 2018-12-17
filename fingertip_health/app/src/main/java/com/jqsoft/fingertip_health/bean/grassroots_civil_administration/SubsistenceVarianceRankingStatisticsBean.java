package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * 低保变化情况排名统计/医疗救助-直接医疗救助支出情况
 * Created by Administrator on 2018-01-03.
 */

public class SubsistenceVarianceRankingStatisticsBean {
    private List<String> xTitle;
    private List<String> xCode;
    private List<SubsistenceVarianceRankingSpecificCategoryBean> list;
    private List<String> total;
    public SubsistenceVarianceRankingStatisticsBean() {
        super();
    }

    public SubsistenceVarianceRankingStatisticsBean(List<String> xTitle, List<SubsistenceVarianceRankingSpecificCategoryBean> list, List<String> total) {
        this.xTitle = xTitle;
        this.list = list;
        this.total = total;
    }

    public List<String> getxTitle() {
        return xTitle;
    }

    public void setxTitle(List<String> xTitle) {
        this.xTitle = xTitle;
    }

    public List<String> getxCode() {
        return xCode;
    }

    public void setxCode(List<String> xCode) {
        this.xCode = xCode;
    }

    public List<SubsistenceVarianceRankingSpecificCategoryBean> getList() {
        return list;
    }

    public void setList(List<SubsistenceVarianceRankingSpecificCategoryBean> list) {
        this.list = list;
    }

    public List<String> getTotal() {
        return total;
    }

    public void setTotal(List<String> total) {
        this.total = total;
    }
}
