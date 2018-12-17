package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/30.
 */

public class VideoBackBean {
    private String videoName;//":" upload_0a1d4e4c_b425_48fc_8ffe_30485c7ce9c7_00000003.mp4",
    private String addDate;
    private String videoPath;///sri/JingQi_Sri_File/upload/video/upload_0a1d4e4c_b425_48fc_8ffe_30485c7ce9c7_00000007.mp4
    private String videoUrl;

    public VideoBackBean() {
    }

    public VideoBackBean(String videoName, String addDate, String videoPath, String videoUrl) {
        this.videoName = videoName;
        this.addDate = addDate;
        this.videoPath = videoPath;
        this.videoUrl = videoUrl;
    }


    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
