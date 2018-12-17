package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/24.
 */

public class DiaoChaFiles {
    private String FileName;//": "调查文件_1.png",
    private String FileType;//": 0,
    private String VideoUrl;//": null,
    private String FilePath;//": "/upload/fujian/20171123bd655a7a-7fa1-465e-afec-ef947ea7381c.png",
    private String Remark;//": "",
    private String AddDate;//": "2017-11-23"

    public DiaoChaFiles() {

    }

    public DiaoChaFiles(String fileName, String fileType, String videoUrl, String filePath, String remark, String addDate) {
        FileName = fileName;
        FileType = fileType;
        VideoUrl = videoUrl;
        FilePath = filePath;
        Remark = remark;
        AddDate = addDate;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getAddDate() {
        return AddDate;
    }

    public void setAddDate(String addDate) {
        AddDate = addDate;
    }
}
