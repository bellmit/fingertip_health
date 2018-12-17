package com.jqsoft.fingertip_health.bean.fingertip;

/**
 * Created by Administrator on 2018-09-13.
 */

public class SignManagementItemBeanForFingertip {
    private int iconId;
    private String title;
    public SignManagementItemBeanForFingertip() {
        super();
    }

    public SignManagementItemBeanForFingertip(int iconId, String title) {
        this.iconId = iconId;
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
