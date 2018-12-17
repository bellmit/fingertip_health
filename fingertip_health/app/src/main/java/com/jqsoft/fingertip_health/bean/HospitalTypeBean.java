package com.jqsoft.fingertip_health.bean;


import com.jqsoft.fingertip_health.feature.HospitalTypeFeature;

import java.util.List;

/**
 * Created by Administrator on 2017-05-18.
 */

public class HospitalTypeBean implements HospitalTypeFeature {
    private String id;
    private int imageId;
    private String title;
    private List<HospitalTypeBean> children;

    public HospitalTypeBean(String id, int imageId, String title, List<HospitalTypeBean> children) {
        this.id = id;
        this.imageId = imageId;
        this.title = title;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HospitalTypeBean> getChildren() {
        return children;
    }

    public void setChildren(List<HospitalTypeBean> children) {
        this.children = children;
    }

    @Override
    public int getFeatureIcon() {
        return getImageId();
    }

    @Override
    public String getFeatureTitle() {
        return getTitle();
    }

    @Override
    public String getFeatureId() {
        return getId();
    }
}
