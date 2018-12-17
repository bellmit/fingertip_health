package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-05-12.
 */

public class PieDataBean {
    private String month;
    private String title;
    private List<PieDataItemBean> list;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PieDataItemBean> getList() {
        return list;
    }

    public void setList(List<PieDataItemBean> list) {
        this.list = list;
    }
}
