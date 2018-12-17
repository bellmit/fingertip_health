package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/2/1.
 */

public class DemoCraticFile {
    private String ADDDATE;//2017-09-01private String  ,
    private String CREATEDATE;///Date(1504251400000+0800)/private String  ,
    private String CREATEUSERID;//00000000-0000-0000-000000000private String  ,
    private String FILEEXTENTION;//MP4private String  ,
    private String FILENAME;//视频文件_1.MP4private String  ,
    private String FILETYPE;
    private String GID;//10722e33-8533-4992-b669-96b842a66935private String  ,
    private String MINZHUPINGYIGID;//a46aceb1-8bce-47a3-88fb-dcb5a10a3b75private String  ,
    private String MODIFYDATE;///Date(1504251400000+0800)/private String  ,
    private String MODIFYUSERID;//00000000-0000-0000-000000000private String  ,
    private String VIDEOURL;///sri/JingQi_Sri_File/upload/video/upload_0a1d4e4c_b425_48fc_8ffe_30485c7ce9c7_00000007.mp4private String
    private String FILEURL;

    public DemoCraticFile() {
    }

    public DemoCraticFile(String ADDDATE, String CREATEDATE, String CREATEUSERID, String FILEEXTENTION, String FILENAME, String FILETYPE, String GID, String MINZHUPINGYIGID, String MODIFYDATE, String MODIFYUSERID, String VIDEOURL, String FILEURL) {
        this.ADDDATE = ADDDATE;
        this.CREATEDATE = CREATEDATE;
        this.CREATEUSERID = CREATEUSERID;
        this.FILEEXTENTION = FILEEXTENTION;
        this.FILENAME = FILENAME;
        this.FILETYPE = FILETYPE;
        this.GID = GID;
        this.MINZHUPINGYIGID = MINZHUPINGYIGID;
        this.MODIFYDATE = MODIFYDATE;
        this.MODIFYUSERID = MODIFYUSERID;
        this.VIDEOURL = VIDEOURL;
        this.FILEURL = FILEURL;
    }

    public String getADDDATE() {
        return ADDDATE;
    }

    public void setADDDATE(String ADDDATE) {
        this.ADDDATE = ADDDATE;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getCREATEUSERID() {
        return CREATEUSERID;
    }

    public void setCREATEUSERID(String CREATEUSERID) {
        this.CREATEUSERID = CREATEUSERID;
    }

    public String getFILEEXTENTION() {
        return FILEEXTENTION;
    }

    public void setFILEEXTENTION(String FILEEXTENTION) {
        this.FILEEXTENTION = FILEEXTENTION;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public String getFILETYPE() {
        return FILETYPE;
    }

    public void setFILETYPE(String FILETYPE) {
        this.FILETYPE = FILETYPE;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getMINZHUPINGYIGID() {
        return MINZHUPINGYIGID;
    }

    public void setMINZHUPINGYIGID(String MINZHUPINGYIGID) {
        this.MINZHUPINGYIGID = MINZHUPINGYIGID;
    }

    public String getMODIFYDATE() {
        return MODIFYDATE;
    }

    public void setMODIFYDATE(String MODIFYDATE) {
        this.MODIFYDATE = MODIFYDATE;
    }

    public String getMODIFYUSERID() {
        return MODIFYUSERID;
    }

    public void setMODIFYUSERID(String MODIFYUSERID) {
        this.MODIFYUSERID = MODIFYUSERID;
    }

    public String getVIDEOURL() {
        return VIDEOURL;
    }

    public void setVIDEOURL(String VIDEOURL) {
        this.VIDEOURL = VIDEOURL;
    }

    public String getFILEURL() {
        return FILEURL;
    }

    public void setFILEURL(String FILEURL) {
        this.FILEURL = FILEURL;
    }
}
