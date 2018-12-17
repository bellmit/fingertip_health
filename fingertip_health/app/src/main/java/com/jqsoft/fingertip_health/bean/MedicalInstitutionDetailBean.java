package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-05-23.
 */

public class MedicalInstitutionDetailBean {
    private float longitude;
    private float latitude;
    private float rating;
    private int evaluationNumber;
    private List<String> evaluationList;
    private String name;
    private String address;
    private String phoneNumber;

    public MedicalInstitutionDetailBean(float longitude, float latitude, float rating, int evaluationNumber, List<String> evaluationList, String name, String address, String phoneNumber) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.rating = rating;
        this.evaluationNumber = evaluationNumber;
        this.evaluationList = evaluationList;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getEvaluationNumber() {
        return evaluationNumber;
    }

    public void setEvaluationNumber(int evaluationNumber) {
        this.evaluationNumber = evaluationNumber;
    }

    public List<String> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<String> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
