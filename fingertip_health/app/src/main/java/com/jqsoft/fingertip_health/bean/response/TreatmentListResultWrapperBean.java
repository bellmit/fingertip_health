package com.jqsoft.fingertip_health.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017-06-02.
 */

public class TreatmentListResultWrapperBean {
    private int page;
    private int size;
    private List<TreatmentListResultBean> list;

    public TreatmentListResultWrapperBean() {
    }

    public TreatmentListResultWrapperBean(int page, int size, List<TreatmentListResultBean> list) {
        this.page = page;
        this.size = size;
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<TreatmentListResultBean> getList() {
        return list;
    }

    public void setList(List<TreatmentListResultBean> list) {
        this.list = list;
    }
}
