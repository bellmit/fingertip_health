package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class SocailHistoryDetailsBean implements Serializable {
    private String name;
    private String sex;
    private String nation;
    private String birthday;
    private String cardNo;
    private String filePath;

    private List<SocialHisDetail> ReliefHisVoList;

    public SocailHistoryDetailsBean() {
    }

    public SocailHistoryDetailsBean(String name, String sex, String nation, String birthday, String cardNo, List<SocialHisDetail> reliefHisVoList, String filePath) {
        this.name = name;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.cardNo = cardNo;
        ReliefHisVoList = reliefHisVoList;
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

    public List<SocialHisDetail> getReliefHisVoList() {
        return ReliefHisVoList;
    }

    public void setReliefHisVoList(List<SocialHisDetail> reliefHisVoList) {
        ReliefHisVoList = reliefHisVoList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}