package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-06-19.
 */

public class MessageListRefreshConfigurationBean {
    private String title;
    private String targetId;
    private String appkey;

    public MessageListRefreshConfigurationBean() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
}
