package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/31.
 */

public class Uploadpic {
    private String goalIP;
    private String filePath;//":"/upload/fujian/201709010fe61e14-2859-4dc3-89fd-fd79a681f805.jpg",
    private String fileName;//":"入户调查_1.jpg",
    private String addDate;//":"2017-09-01",

    public Uploadpic() {
    }

    public Uploadpic(String goalIP, String filePath, String fileName, String addDate) {
        this.goalIP = goalIP;
        this.filePath = filePath;
        this.fileName = fileName;
        this.addDate = addDate;
    }

    public String getGoalIP() {
        return goalIP;
    }

    public void setGoalIP(String goalIP) {
        this.goalIP = goalIP;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
}
