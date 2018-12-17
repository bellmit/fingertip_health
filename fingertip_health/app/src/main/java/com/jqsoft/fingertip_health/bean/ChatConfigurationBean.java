package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-06-01.
 */

public class ChatConfigurationBean {
    private boolean shouldRefresh;
    private String fromId;
    private String toId;

    public boolean isShouldRefresh() {
        return shouldRefresh;
    }

    public void setShouldRefresh(boolean shouldRefresh) {
        this.shouldRefresh = shouldRefresh;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}
