package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/1.
 */

public class DetailUplodeFile {
    private String fileCode;
    private String fileName;
    private String filePath;


    public DetailUplodeFile() {
    }



    public DetailUplodeFile(String fileCode, String fileName, String filePath) {
        this.fileCode = fileCode;
        this.fileName = fileName;
        this.filePath = filePath;

    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
