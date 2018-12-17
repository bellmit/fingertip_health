package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * Created by Mars on 2018/1/26.
 */

public class CenterListBean {

    private String id;
    private String name;
//    private  CollectionUrlBean collectionUrl;
    private   String  collectionUrl;

    public CenterListBean() {
    }

    public CenterListBean(String id, String name, String collectionUrl) {
        this.id = id;
        this.name = name;
        this.collectionUrl = collectionUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollectionUrl() {
        return collectionUrl;
    }

    public void setCollectionUrl(String collectionUrl) {
        this.collectionUrl = collectionUrl;
    }
}
