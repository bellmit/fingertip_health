package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/11.
 */

public class BaseInfoHisdata {
    private String name;
    private String sex;
    private String nation;
    private String birthday;
    private String cardNo;

    public BaseInfoHisdata() {
    }

    public BaseInfoHisdata(String name, String sex, String nation, String birthday, String cardNo) {
        this.name = name;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.cardNo = cardNo;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }


}
