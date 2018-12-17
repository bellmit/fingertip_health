package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class DemoCraticBaseBean {
    private String goalIP;
    private List<DemoCraticFile> fileList;
    private DemoCraticMessage detailMassage;

    public DemoCraticBaseBean() {
    }

    public DemoCraticBaseBean(String goalIP, List<DemoCraticFile> fileList, DemoCraticMessage detailMassage) {
        this.goalIP = goalIP;
        this.fileList = fileList;
        this.detailMassage = detailMassage;
    }

    public String getGoalIP() {
        return goalIP;
    }

    public void setGoalIP(String goalIP) {
        this.goalIP = goalIP;
    }

    public List<DemoCraticFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<DemoCraticFile> fileList) {
        this.fileList = fileList;
    }

    public DemoCraticMessage getDetailMassage() {
        return detailMassage;
    }

    public void setDetailMassage(DemoCraticMessage detailMassage) {
        this.detailMassage = detailMassage;
    }
}
