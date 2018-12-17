package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;
import java.util.List;

/**
 * 我的收藏
 */

public class PersonCollectionBean implements Serializable {
    private List<HelpListBean> helpList ;
    private List<CenterListBean> centerList ;
    private List<JzzListBean> jzzList;

    public PersonCollectionBean() {
    }

    public PersonCollectionBean(List<HelpListBean> helpList, List<CenterListBean> centerList, List<JzzListBean> jzzList) {
        this.helpList = helpList;
        this.centerList = centerList;
        this.jzzList = jzzList;
    }

    public List<HelpListBean> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpListBean> helpList) {
        this.helpList = helpList;
    }

    public List<CenterListBean> getCenterList() {
        return centerList;
    }

    public void setCenterList(List<CenterListBean> centerList) {
        this.centerList = centerList;
    }

    public List<JzzListBean> getJzzList() {
        return jzzList;
    }

    public void setJzzList(List<JzzListBean> jzzList) {
        this.jzzList = jzzList;
    }
}
