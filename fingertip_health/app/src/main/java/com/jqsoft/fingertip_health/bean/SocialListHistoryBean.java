package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/3/22.
 */

public class SocialListHistoryBean {
    private String name;
    private String sex;
    private String cardNo;
     private String filePath;



    public SocialListHistoryBean() {
    }

    public SocialListHistoryBean(String name, String sex, String cardNo,String filePath) {
        this.name = name;
        this.sex = sex;
        this.cardNo = cardNo;
        this.filePath = filePath;
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
