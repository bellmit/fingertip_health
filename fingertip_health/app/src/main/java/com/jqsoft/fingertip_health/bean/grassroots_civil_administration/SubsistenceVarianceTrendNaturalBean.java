package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保变化情况趋势统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceVarianceTrendNaturalBean {
    private String monthName;
    private String newQuantity;
    private String reviewQuantity;
    private String deleteQuantity;
    public SubsistenceVarianceTrendNaturalBean() {
        super();
    }

    public SubsistenceVarianceTrendNaturalBean(String monthName, String newQuantity, String reviewQuantity, String deleteQuantity) {
        this.monthName = monthName;
        this.newQuantity = newQuantity;
        this.reviewQuantity = reviewQuantity;
        this.deleteQuantity = deleteQuantity;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
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
