package com.jqsoft.fingertip_health.bean.response;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017-05-19.
 */

public class ChatBean implements MultiItemEntity {
    public static final int OTHER_PERSON=0;
    public static final int SELF=1;

    private int type;
    private String date;//2017-05-19 15:44:20
    private String fromId;
    private String fromPersonName;
    private String toId;
    private String toPersonName;
    private String content;

    public ChatBean(int type, String date, String fromId, String fromPersonName, String toId, String toPersonName, String content) {
        this.type = type;
        this.date = date;
        this.fromId = fromId;
        this.fromPersonName = fromPersonName;
        this.toId = toId;
        this.toPersonName = toPersonName;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromPersonName() {
        return fromPersonName;
    }

    public void setFromPersonName(String fromPersonName) {
        this.fromPersonName = fromPersonName;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToPersonName() {
        return toPersonName;
    }

    public void setToPersonName(String toPersonName) {
        this.toPersonName = toPersonName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
