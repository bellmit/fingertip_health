package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/15.
 */

public class HouseApplying {
    private String DSQRQ;
    private String GID;// "7FC05D69-FE82-41AF-AB5B-B3DA19DBDA49",
    private String IJTRKS;// 2.0,
    private String SADRR;// "庐阳区大杨镇十张村",
    private String SHZIDCARD;// "342427199202279285",
    private String SHZNAME;// "测试1",
    private String SAREACODE;// "340103",
    private String SAREANAME;// "庐阳区",
    private String SFAMILYNO;// "F407256074764266",
    private String SFAMILYYHZH;// "",
    private String SHJXZ;// 0.0,

    public HouseApplying() {

    }

    public HouseApplying(String DSQRQ, String GID, String IJTRKS, String SADRR, String SHZIDCARD, String SHZNAME, String SAREACODE, String SAREANAME, String SFAMILYNO, String SFAMILYYHZH, String SHJXZ) {
        this.DSQRQ = DSQRQ;
        this.GID = GID;
        this.IJTRKS = IJTRKS;
        this.SADRR = SADRR;
        this.SHZIDCARD = SHZIDCARD;
        this.SHZNAME = SHZNAME;
        this.SAREACODE = SAREACODE;
        this.SAREANAME = SAREANAME;
        this.SFAMILYNO = SFAMILYNO;
        this.SFAMILYYHZH = SFAMILYYHZH;
        this.SHJXZ = SHJXZ;
    }

    public String getDSQRQ() {
        return DSQRQ;
    }

    public void setDSQRQ(String DSQRQ) {
        this.DSQRQ = DSQRQ;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getIJTRKS() {
        return IJTRKS;
    }

    public void setIJTRKS(String IJTRKS) {
        this.IJTRKS = IJTRKS;
    }

    public String getSADRR() {
        return SADRR;
    }

    public void setSADRR(String SADRR) {
        this.SADRR = SADRR;
    }

    public String getSHZIDCARD() {
        return SHZIDCARD;
    }

    public void setSHZIDCARD(String SHZIDCARD) {
        this.SHZIDCARD = SHZIDCARD;
    }

    public String getSHZNAME() {
        return SHZNAME;
    }

    public void setSHZNAME(String SHZNAME) {
        this.SHZNAME = SHZNAME;
    }

    public String getSAREACODE() {
        return SAREACODE;
    }

    public void setSAREACODE(String SAREACODE) {
        this.SAREACODE = SAREACODE;
    }

    public String getSAREANAME() {
        return SAREANAME;
    }

    public void setSAREANAME(String SAREANAME) {
        this.SAREANAME = SAREANAME;
    }

    public String getSFAMILYNO() {
        return SFAMILYNO;
    }

    public void setSFAMILYNO(String SFAMILYNO) {
        this.SFAMILYNO = SFAMILYNO;
    }

    public String getSFAMILYYHZH() {
        return SFAMILYYHZH;
    }

    public void setSFAMILYYHZH(String SFAMILYYHZH) {
        this.SFAMILYYHZH = SFAMILYYHZH;
    }

    public String getSHJXZ() {
        return SHJXZ;
    }

    public void setSHJXZ(String SHJXZ) {
        this.SHJXZ = SHJXZ;
    }
}
