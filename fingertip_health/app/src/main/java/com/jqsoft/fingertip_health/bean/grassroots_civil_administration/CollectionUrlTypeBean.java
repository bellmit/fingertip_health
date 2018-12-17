package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;


public class CollectionUrlTypeBean implements Serializable {
    private String collectioId;

    public CollectionUrlTypeBean(String collectioId) {
        this.collectioId = collectioId;
    }

    public String getCollectioId() {
        return collectioId;

    }

    public void setCollectioId(String collectioId) {
        this.collectioId = collectioId;
    }
}
