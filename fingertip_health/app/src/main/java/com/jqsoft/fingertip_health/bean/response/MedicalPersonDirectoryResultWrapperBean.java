package com.jqsoft.fingertip_health.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */

public class MedicalPersonDirectoryResultWrapperBean {
    private int page;
    private int size;
    private List<MedicalPersonDirectoryResultBean> list;
    public MedicalPersonDirectoryResultWrapperBean() {
        super();
    }

    public MedicalPersonDirectoryResultWrapperBean(int page, int size, List<MedicalPersonDirectoryResultBean> list) {
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

    public List<MedicalPersonDirectoryResultBean> getList() {
        return list;
    }

    public void setList(List<MedicalPersonDirectoryResultBean> list) {
        this.list = list;
    }
}
