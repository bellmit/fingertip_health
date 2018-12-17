package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-06-23.
 */

public class ImageAndTextBean {
    private int id;
    private int imageId;
    private String title;
    public ImageAndTextBean() {
        super();
    }

    public ImageAndTextBean(int id, int imageId, String title) {
        this.id = id;
        this.imageId = imageId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
