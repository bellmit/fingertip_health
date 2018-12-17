package com.jqsoft.fingertip_health.bean.resident;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jqsoft.fingertip_health.util.Util;

/**
 * Created by Administrator on 2017-08-18.
 * 智能履约提醒
 */

public class RemindBean implements MultiItemEntity {
    public static final int TYPE_REMIND=0;
    public static final int TYPE_MESSAGE=1;

    private int type;


    private int imageId;
    private  String contentName;//                             服务项目名称
    private  String packageName;//                           服务包名称
    private  String remindDT;//                                提醒时间

    public String getCompressedDateCombinedString(){
        String s = Util.trimString(remindDT);
        s = Util.getYearMonthDayFromFullString(s);
        return "您的\""+packageName+"\"的执行项目\""+contentName+"\"应于"+s+"进行服务,请提前与家庭医生联系!";
    }

    public String getCombinedString(){
        return "您的\""+packageName+"\"的执行项目\""+contentName+"\"应于"+remindDT+"进行服务,请提前与家庭医生联系!";
    }

    public RemindBean() {
        super();
    }

    public RemindBean(int type, int imageId, String contentName, String packageName, String remindDT) {
        this.type = type;
        this.imageId = imageId;
        this.contentName = contentName;
        this.packageName = packageName;
        this.remindDT = remindDT;
    }

    @Override
    public int getItemType() {
        return type;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getRemindDT() {
        return remindDT;
    }

    public void setRemindDT(String remindDT) {
        this.remindDT = remindDT;
    }
}
