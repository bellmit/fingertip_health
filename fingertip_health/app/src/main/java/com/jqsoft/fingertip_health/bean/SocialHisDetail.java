package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/11.
 */

public class SocialHisDetail implements Serializable {

    private String moneyGrant;
    private String itemName;
    private String moneyDate;
    private String issuingAgency;
    private String dataType;



    public SocialHisDetail(String moneyGrant, String itemName, String moneyDate, String issuingAgency,String dataType) {
        this.moneyGrant = moneyGrant;
        this.itemName = itemName;
        this.moneyDate = moneyDate;
        this.issuingAgency = issuingAgency;
        this.dataType = dataType;
    }

    public String getMoneyGrant() {
        return moneyGrant;
    }

    public void setMoneyGrant(String moneyGrant) {
        this.moneyGrant = moneyGrant;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(String moneyDate) {
        this.moneyDate = moneyDate;
    }

    public String getIssuingAgency() {
        return issuingAgency;
    }

    public void setIssuingAgency(String issuingAgency) {
        this.issuingAgency = issuingAgency;
    }
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public SocialHisDetail() {

    }
}
