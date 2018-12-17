package com.jqsoft.fingertip_health.bean.resident;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-08-18.
 * 家庭医生签约政策解读
 */

public class PolicyBean implements Serializable {
    private String key;//                               主键
    private String title;//                               标题
    private String createDT;//                           时间
    private String content;//                            内容
    public PolicyBean() {
        super();
    }

    public PolicyBean(String key, String title, String createDT, String content) {
        this.key = key;
        this.title = title;
        this.createDT = createDT;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDT() {
        return createDT;
    }

    public void setCreateDT(String createDT) {
        this.createDT = createDT;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
