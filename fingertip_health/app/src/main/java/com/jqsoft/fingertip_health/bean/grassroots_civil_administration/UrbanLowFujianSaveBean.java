package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * 附件列表
 * Created by Administrator on 2017-12-27.
 */

public class UrbanLowFujianSaveBean implements Serializable {
    private String fileName;
    private String filePath;
    private String fileCode;
    private String fileId;

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

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
