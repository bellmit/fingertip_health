package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class HouseHoldeBackBean {
    private String goalIP;
    private List<DiaoChaFiles> diaoChaFiles;
    private DiaoChaBean diaoCha;

    public HouseHoldeBackBean() {
    }

    public HouseHoldeBackBean(String goalIP, List<DiaoChaFiles> diaoChaFiles, DiaoChaBean diaoCha) {
        this.goalIP = goalIP;
        this.diaoChaFiles = diaoChaFiles;
        this.diaoCha = diaoCha;
    }

    public String getGoalIP() {
        return goalIP;
    }

    public void setGoalIP(String goalIP) {
        this.goalIP = goalIP;
    }

    public List<DiaoChaFiles> getDiaoChaFiles() {
        return diaoChaFiles;
    }

    public void setDiaoChaFiles(List<DiaoChaFiles> diaoChaFiles) {
        this.diaoChaFiles = diaoChaFiles;
    }

    public DiaoChaBean getDiaoCha() {
        return diaoCha;
    }

    public void setDiaoCha(DiaoChaBean diaoCha) {
        this.diaoCha = diaoCha;
    }
}
