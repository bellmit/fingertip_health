package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/24.
 */

public class DemocraticAppraisalBean {
    private String FILECOUNT;
    private String GID;
    private String PINGYIDATE;
    private String PINGYIMEMO;
    private String PINGYIRENNAME;
    private String PINGYIRESULTDESC;

    public DemocraticAppraisalBean() {
    }

    public DemocraticAppraisalBean(String FILECOUNT, String GID, String PINGYIDATE, String PINGYIMEMO, String PINGYIRENNAME, String PINGYIRESULTDESC) {
        this.FILECOUNT = FILECOUNT;
        this.GID = GID;
        this.PINGYIDATE = PINGYIDATE;
        this.PINGYIMEMO = PINGYIMEMO;
        this.PINGYIRENNAME = PINGYIRENNAME;
        this.PINGYIRESULTDESC = PINGYIRESULTDESC;
    }

    public String getFILECOUNT() {
        return FILECOUNT;
    }

    public void setFILECOUNT(String FILECOUNT) {
        this.FILECOUNT = FILECOUNT;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getPINGYIDATE() {
        return PINGYIDATE;
    }

    public void setPINGYIDATE(String PINGYIDATE) {
        this.PINGYIDATE = PINGYIDATE;
    }

    public String getPINGYIMEMO() {
        return PINGYIMEMO;
    }

    public void setPINGYIMEMO(String PINGYIMEMO) {
        this.PINGYIMEMO = PINGYIMEMO;
    }

    public String getPINGYIRENNAME() {
        return PINGYIRENNAME;
    }

    public void setPINGYIRENNAME(String PINGYIRENNAME) {
        this.PINGYIRENNAME = PINGYIRENNAME;
    }

    public String getPINGYIRESULTDESC() {
        return PINGYIRESULTDESC;
    }

    public void setPINGYIRESULTDESC(String PINGYIRESULTDESC) {
        this.PINGYIRESULTDESC = PINGYIRESULTDESC;
    }
}
