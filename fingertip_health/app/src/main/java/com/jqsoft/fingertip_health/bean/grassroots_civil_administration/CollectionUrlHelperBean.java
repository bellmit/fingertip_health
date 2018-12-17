package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * Created by Mars on 2018/1/26.
 */

public class CollectionUrlHelperBean implements Serializable {
    private String itemId;
    private String collectionType;
    private String name;

    public CollectionUrlHelperBean(String itemId, String collectionType, String name) {
        this.itemId = itemId;
        this.collectionType = collectionType;
        this.name = name;
    }

    public CollectionUrlHelperBean() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

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
