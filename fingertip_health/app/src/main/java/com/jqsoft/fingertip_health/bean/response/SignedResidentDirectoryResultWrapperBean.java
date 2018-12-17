package com.jqsoft.fingertip_health.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */

public class SignedResidentDirectoryResultWrapperBean {
    private int page;
    private int size;
    private List<SignedResidentDirectoryResultBean> list;
    public SignedResidentDirectoryResultWrapperBean() {
        super();
    }

    public SignedResidentDirectoryResultWrapperBean(int page, int size, List<SignedResidentDirectoryResultBean> list) {
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

    public List<SignedResidentDirectoryResultBean> getList() {
        return list;
    }

    public void setList(List<SignedResidentDirectoryResultBean> list) {
        this.list = list;
    }
}
