package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public class HouseFileBean {
    private String goalIP;
    private List<HouseHoldFileList> fileList;

    public HouseFileBean() {
    }

    public HouseFileBean(String goalIP, List<HouseHoldFileList> fileList) {
        this.goalIP = goalIP;
        this.fileList = fileList;
    }

    public String getGoalIP() {
        return goalIP;
    }

    public void setGoalIP(String goalIP) {
        this.goalIP = goalIP;
    }

    public List<HouseHoldFileList> getFileList() {
        return fileList;
    }

    public void setFileList(List<HouseHoldFileList> fileList) {
        this.fileList = fileList;
    }
}
