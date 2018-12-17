package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/1.
 */

public class DetaiFamilMember {
    private String name;
    private String sex;
    private String cardNo;
    private String relation;

    public DetaiFamilMember() {
    }

    public DetaiFamilMember(String name, String sex, String cardNo, String relation) {
        this.name = name;
        this.sex = sex;
        this.cardNo = cardNo;
        this.relation = relation;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
