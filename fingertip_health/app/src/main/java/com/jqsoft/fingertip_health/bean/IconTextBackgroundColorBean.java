package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-05-17.
 */

public class IconTextBackgroundColorBean {
    private int iconResourceId;
    private String text;
    private int backgroundColor;

    public IconTextBackgroundColorBean(int iconResourceId, String text, int backgroundColor) {
        this.iconResourceId = iconResourceId;
        this.text = text;
        this.backgroundColor = backgroundColor;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
