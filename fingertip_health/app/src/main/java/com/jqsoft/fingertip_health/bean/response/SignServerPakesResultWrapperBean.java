package com.jqsoft.fingertip_health.bean.response;

import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;

import java.util.List;

/**
 * Created by Administrator on 2017-06-22.
 */

public class SignServerPakesResultWrapperBean {
    private int page;
    private int size;
    private List<SignSeverPakesBeanList> list;

    public SignServerPakesResultWrapperBean() {
        super();
    }

    public SignServerPakesResultWrapperBean(int page, int size, List<SignSeverPakesBeanList> list) {
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

    public List<SignSeverPakesBeanList> getList() {
        return list;
    }

    public void setList(List<SignSeverPakesBeanList> list) {
        this.list = list;
    }
}
