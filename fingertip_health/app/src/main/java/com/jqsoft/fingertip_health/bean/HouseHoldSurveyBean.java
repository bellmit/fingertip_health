package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29.
 */

public class HouseHoldSurveyBean {
    private List<HouseApplying> applyList;
    private List<HouseCheckingBean> reviewList;

    public HouseHoldSurveyBean() {
    }

    public HouseHoldSurveyBean(List<HouseApplying> applyList, List<HouseCheckingBean> reviewList) {
        this.applyList = applyList;
        this.reviewList = reviewList;
    }

    public List<HouseApplying> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<HouseApplying> applyList) {
        this.applyList = applyList;
    }

    public List<HouseCheckingBean> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<HouseCheckingBean> reviewList) {
        this.reviewList = reviewList;
    }
}
