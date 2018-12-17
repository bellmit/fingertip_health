package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/22.
 */

public class HouseHoldFileList {
    private String DateStr;//2017-09-11",
    private String FilePath;///upload/YMZ/4a38d753-ef18-4526-83a8-898a8252c109.png",
    private String FileTypeCode;//19",
    private String FileTypeName;//申请书",
    private String ShowName;//申请书_1",
    private String UploadDate;///Date(1505100007000)/"

    public HouseHoldFileList() {
    }

    public HouseHoldFileList(String dateStr, String filePath, String fileTypeCode, String fileTypeName, String showName, String uploadDate) {
        DateStr = dateStr;
        FilePath = filePath;
        FileTypeCode = fileTypeCode;
        FileTypeName = fileTypeName;
        ShowName = showName;
        UploadDate = uploadDate;
    }

    public String getDateStr() {
        return DateStr;
    }

    public void setDateStr(String dateStr) {
        DateStr = dateStr;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getFileTypeCode() {
        return FileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        FileTypeCode = fileTypeCode;
    }

    public String getFileTypeName() {
        return FileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        FileTypeName = fileTypeName;
    }

    public String getShowName() {
        return ShowName;
    }

    public void setShowName(String showName) {
        ShowName = showName;
    }

    public String getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(String uploadDate) {
        UploadDate = uploadDate;
    }
}
