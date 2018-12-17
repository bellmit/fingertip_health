package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017/12/27.
 */

public class SocialAssistanceObjectBean {
    private String batchNo;
    private String name;
    private String sex;
    private String cardNo;
    private String filePath;



    public SocialAssistanceObjectBean() {

    }

    public SocialAssistanceObjectBean(String batchNo, String name, String sex, String cardNo,String filePath) {
        this.batchNo = batchNo;
        this.name = name;
        this.sex = sex;
        this.cardNo = cardNo;
        this.filePath = filePath;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
