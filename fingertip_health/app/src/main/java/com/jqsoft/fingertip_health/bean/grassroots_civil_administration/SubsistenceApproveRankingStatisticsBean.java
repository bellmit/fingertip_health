package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * 低保审批情况排名统计
 * Created by Administrator on 2018-01-03.
 */

public class SubsistenceApproveRankingStatisticsBean {
    private List<String> xTitle;
    private List<String> xCode;
    private List<SubsistenceApproveRankingSpecificCategoryBean> list;
    public SubsistenceApproveRankingStatisticsBean() {
        super();
    }

    public SubsistenceApproveRankingStatisticsBean(List<String> xTitle, List<SubsistenceApproveRankingSpecificCategoryBean> list) {
        this.xTitle = xTitle;
        this.list = list;
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

    public List<SubsistenceApproveRankingSpecificCategoryBean> getList() {
        return list;
    }

    public void setList(List<SubsistenceApproveRankingSpecificCategoryBean> list) {
        this.list = list;
    }
}
