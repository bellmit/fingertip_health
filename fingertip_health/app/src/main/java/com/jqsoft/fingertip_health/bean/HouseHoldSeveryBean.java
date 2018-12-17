package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/22.
 */

public class HouseHoldSeveryBean {
    private String DIAOCHARENNAME;//來文龍",
    private String GCREATEUSERID;//00000000-0000-0000-0000-000000000000",
    private String WJS;// 2,
    private String DMODIFYDATE;//2017-11-24",
    private String DCREATEDATE;//2017-08-25",
    private String DIAOCHAJIELUN;//不通過",
    private String DIAOCHADATE;//2017-11-11",
    private String GMODIFYUSERID;//00000000-0000-0000-0000-000000000000",
    private String GFAMILYINFOID;//51FAC060-889E-4A7A-B023-D0FD658C4432",
    private String GID;//9599088C-5E58-4886-8BA1-125BBAC4DDA0"

    public HouseHoldSeveryBean() {
    }

    public HouseHoldSeveryBean(String DIAOCHARENNAME, String GCREATEUSERID, String WJS, String DMODIFYDATE, String DCREATEDATE, String DIAOCHAJIELUN, String DIAOCHADATE, String GMODIFYUSERID, String GFAMILYINFOID, String GID) {
        this.DIAOCHARENNAME = DIAOCHARENNAME;
        this.GCREATEUSERID = GCREATEUSERID;
        this.WJS = WJS;
        this.DMODIFYDATE = DMODIFYDATE;
        this.DCREATEDATE = DCREATEDATE;
        this.DIAOCHAJIELUN = DIAOCHAJIELUN;
        this.DIAOCHADATE = DIAOCHADATE;
        this.GMODIFYUSERID = GMODIFYUSERID;
        this.GFAMILYINFOID = GFAMILYINFOID;
        this.GID = GID;
    }

    public String getDIAOCHARENNAME() {
        return DIAOCHARENNAME;
    }

    public void setDIAOCHARENNAME(String DIAOCHARENNAME) {
        this.DIAOCHARENNAME = DIAOCHARENNAME;
    }

    public String getGCREATEUSERID() {
        return GCREATEUSERID;
    }

    public void setGCREATEUSERID(String GCREATEUSERID) {
        this.GCREATEUSERID = GCREATEUSERID;
    }

    public String getWJS() {
        return WJS;
    }

    public void setWJS(String WJS) {
        this.WJS = WJS;
    }

    public String getDMODIFYDATE() {
        return DMODIFYDATE;
    }

    public void setDMODIFYDATE(String DMODIFYDATE) {
        this.DMODIFYDATE = DMODIFYDATE;
    }

    public String getDCREATEDATE() {
        return DCREATEDATE;
    }

    public void setDCREATEDATE(String DCREATEDATE) {
        this.DCREATEDATE = DCREATEDATE;
    }

    public String getDIAOCHAJIELUN() {
        return DIAOCHAJIELUN;
    }

    public void setDIAOCHAJIELUN(String DIAOCHAJIELUN) {
        this.DIAOCHAJIELUN = DIAOCHAJIELUN;
    }

    public String getDIAOCHADATE() {
        return DIAOCHADATE;
    }

    public void setDIAOCHADATE(String DIAOCHADATE) {
        this.DIAOCHADATE = DIAOCHADATE;
    }

    public String getGMODIFYUSERID() {
        return GMODIFYUSERID;
    }

    public void setGMODIFYUSERID(String GMODIFYUSERID) {
        this.GMODIFYUSERID = GMODIFYUSERID;
    }

    public String getGFAMILYINFOID() {
        return GFAMILYINFOID;
    }

    public void setGFAMILYINFOID(String GFAMILYINFOID) {
        this.GFAMILYINFOID = GFAMILYINFOID;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }
}
