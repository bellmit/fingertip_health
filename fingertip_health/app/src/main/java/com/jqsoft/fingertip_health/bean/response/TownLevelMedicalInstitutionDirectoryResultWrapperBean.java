package com.jqsoft.fingertip_health.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */

public class TownLevelMedicalInstitutionDirectoryResultWrapperBean {
    private int page;
    private int size;
    private List<TownLevelMedicalInstitutionDirectoryResultBean> list;
    public TownLevelMedicalInstitutionDirectoryResultWrapperBean() {
        super();
    }

    public TownLevelMedicalInstitutionDirectoryResultWrapperBean(int page, int size, List<TownLevelMedicalInstitutionDirectoryResultBean> list) {
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

    public List<TownLevelMedicalInstitutionDirectoryResultBean> getList() {
        return list;
    }

    public void setList(List<TownLevelMedicalInstitutionDirectoryResultBean> list) {
        this.list = list;
    }
}
