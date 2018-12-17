package com.jqsoft.fingertip_health.bean.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-19.
 */

public class ChatListWrapperBean {
    private int page;
    private int size;
    private List<ChatBean> list;

    public ChatListWrapperBean() {
    }

    public ChatListWrapperBean(int page, int size, ArrayList<ChatBean> list) {
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

    public List<ChatBean> getList() {
        return list;
    }

    public void setList(List<ChatBean> list) {
        this.list = list;
    }
}
