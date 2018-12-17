package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保变化情况排名统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceVarianceRankingNaturalBean {
    private String districtName;
    private String newQuantity;
    private String reviewQuantity;
    private String deleteQuantity;
    public SubsistenceVarianceRankingNaturalBean() {
        super();
    }

    public SubsistenceVarianceRankingNaturalBean(String districtName, String newQuantity, String reviewQuantity, String deleteQuantity) {

        this.districtName = districtName;
        this.newQuantity = newQuantity;
        this.reviewQuantity = reviewQuantity;
        this.deleteQuantity = deleteQuantity;
    }


    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(String newQuantity) {
        this.newQuantity = newQuantity;
    }

    public String getReviewQuantity() {
        return reviewQuantity;
    }

    public void setReviewQuantity(String reviewQuantity) {
        this.reviewQuantity = reviewQuantity;
    }

    public String getDeleteQuantity() {
        return deleteQuantity;
    }

    public void setDeleteQuantity(String deleteQuantity) {
        this.deleteQuantity = deleteQuantity;
    }

}
