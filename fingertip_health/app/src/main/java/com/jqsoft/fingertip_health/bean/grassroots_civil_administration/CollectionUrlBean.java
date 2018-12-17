package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * 受理中心收藏
 * Created by Administrator on 2017-12-27.
 */

public class CollectionUrlBean implements Serializable {

    public String getRecepId() {
        return recepId;
    }

    public void setRecepId(String recepId) {
        this.recepId = recepId;
    }

    private String recepId;
    private String collectionType;
    private String name;



    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
