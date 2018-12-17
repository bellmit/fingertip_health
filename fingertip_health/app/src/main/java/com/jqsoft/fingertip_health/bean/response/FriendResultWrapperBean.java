package com.jqsoft.fingertip_health.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017-06-08.
 */

public class FriendResultWrapperBean {
    private int page;
    private int size;
    private List<FriendResultBean> list;

    public FriendResultWrapperBean() {
    }

    public FriendResultWrapperBean(int page, int size, List<FriendResultBean> list) {
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

    public List<FriendResultBean> getList() {
        return list;
    }

    public void setList(List<FriendResultBean> list) {
        this.list = list;
    }
}
