package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017/5/14.
 */

public class MineBean {
    private String announcementNumber;
    private String rating;
    private String subjectToBeReviewedNumber;
    private String messageNumber;

    public MineBean(String announcementNumber, String rating, String subjectToBeReviewedNumber, String messageNumber) {
        this.announcementNumber = announcementNumber;
        this.rating = rating;
        this.subjectToBeReviewedNumber = subjectToBeReviewedNumber;
        this.messageNumber = messageNumber;
    }

    public String getAnnouncementNumber() {
        return announcementNumber;
    }

    public void setAnnouncementNumber(String announcementNumber) {
        this.announcementNumber = announcementNumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSubjectToBeReviewedNumber() {
        return subjectToBeReviewedNumber;
    }

    public void setSubjectToBeReviewedNumber(String subjectToBeReviewedNumber) {
        this.subjectToBeReviewedNumber = subjectToBeReviewedNumber;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }
}
