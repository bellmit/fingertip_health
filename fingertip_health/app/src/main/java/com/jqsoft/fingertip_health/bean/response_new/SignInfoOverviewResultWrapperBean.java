package com.jqsoft.fingertip_health.bean.response_new;

import java.util.List;

/**
 * Created by Administrator on 2017-06-29.
 */

public class SignInfoOverviewResultWrapperBean {
    private List<SignInfoOverviewResultBean> list;
    public SignInfoOverviewResultWrapperBean() {
        super();
    }

    public SignInfoOverviewResultWrapperBean(List<SignInfoOverviewResultBean> list) {
        this.list = list;
    }

    public List<SignInfoOverviewResultBean> getList() {
        return list;
    }

    public void setList(List<SignInfoOverviewResultBean> list) {
        this.list = list;
    }
}
