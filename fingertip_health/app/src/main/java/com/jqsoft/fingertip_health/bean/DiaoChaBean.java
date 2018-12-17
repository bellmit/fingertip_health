package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/24.
 */

public class DiaoChaBean {
    private String GFAMILYINFOID;//": "51FAC060-889E-4A7A-B023-D0FD658C4432",
    private String DiaoChaJieLun;//": "不通過",
    private String DiaoChaRenName;//": "來文龍",
    private String DiaoChaDate;//": "2017-11-11",
    private String GID;//": "9599088C-5E58-4886-8BA1-125BBAC4DDA0"

    public DiaoChaBean() {
    }

    public DiaoChaBean(String GFAMILYINFOID, String diaoChaJieLun, String diaoChaRenName, String diaoChaDate, String GID) {
        this.GFAMILYINFOID = GFAMILYINFOID;
        DiaoChaJieLun = diaoChaJieLun;
        DiaoChaRenName = diaoChaRenName;
        DiaoChaDate = diaoChaDate;
        this.GID = GID;
    }

    public String getGFAMILYINFOID() {
        return GFAMILYINFOID;
    }

    public void setGFAMILYINFOID(String GFAMILYINFOID) {
        this.GFAMILYINFOID = GFAMILYINFOID;
    }

    public String getDiaoChaJieLun() {
        return DiaoChaJieLun;
    }

    public void setDiaoChaJieLun(String diaoChaJieLun) {
        DiaoChaJieLun = diaoChaJieLun;
    }

    public String getDiaoChaRenName() {
        return DiaoChaRenName;
    }

    public void setDiaoChaRenName(String diaoChaRenName) {
        DiaoChaRenName = diaoChaRenName;
    }

    public String getDiaoChaDate() {
        return DiaoChaDate;
    }

    public void setDiaoChaDate(String diaoChaDate) {
        DiaoChaDate = diaoChaDate;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }
}
