package com.jqsoft.fingertip_health.bean.resident;

import java.util.List;

/**
 * Created by Administrator on 2017-08-25.
 * 提醒列表与消息内容及数量
 */

public class RemindAndMessageBean {
    private String content;//最新消息
    private String total;//消息总数
    private List<RemindBean> list;
    public RemindAndMessageBean() {
        super();
    }

    public RemindAndMessageBean(String content, String total, List<RemindBean> list) {
        this.content = content;
        this.total = total;
        this.list = list;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<RemindBean> getList() {
        return list;
    }

    public void setList(List<RemindBean> list) {
        this.list = list;
    }
}
