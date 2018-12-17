package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/2/1.
 */

public class DemoCraticMessage {
    private String CREATEDATE;///Date(1504251400000+0800)/",
    private String GFAMILYINFOID;//7FC05D69-FE82-41AF-AB5B-B3DA19DBDA49",
    private String GID;//a46aceb1-8bce-47a3-88fb-dcb5a10a3b75",
    private String PINGYIDATE;//2017-09-01",
    private String PINGYIMEMO;//评议不通过",
    private String PINGYIRENNAME;//4",
    private String PINGYIRESULT;//": 0.0,
    private String PINGYIRESULTDESC;//不通过"

    public DemoCraticMessage() {
    }

    public DemoCraticMessage(String CREATEDATE, String GFAMILYINFOID, String GID, String PINGYIDATE, String PINGYIMEMO, String PINGYIRENNAME, String PINGYIRESULT, String PINGYIRESULTDESC) {
        this.CREATEDATE = CREATEDATE;
        this.GFAMILYINFOID = GFAMILYINFOID;
        this.GID = GID;
        this.PINGYIDATE = PINGYIDATE;
        this.PINGYIMEMO = PINGYIMEMO;
        this.PINGYIRENNAME = PINGYIRENNAME;
        this.PINGYIRESULT = PINGYIRESULT;
        this.PINGYIRESULTDESC = PINGYIRESULTDESC;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
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

    public String getPINGYIRESULT() {
        return PINGYIRESULT;
    }

    public void setPINGYIRESULT(String PINGYIRESULT) {
        this.PINGYIRESULT = PINGYIRESULT;
    }

    public String getPINGYIRESULTDESC() {
        return PINGYIRESULTDESC;
    }

    public void setPINGYIRESULTDESC(String PINGYIRESULTDESC) {
        this.PINGYIRESULTDESC = PINGYIRESULTDESC;
    }
}
