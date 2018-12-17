package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/1.
 */

public class DetailHelpResult {
    private String itemName;
    private String moneyGrant;
    private String moneyDate;
    private String issuingAgency;
    private String dataType;


    public DetailHelpResult() {
    }



    public DetailHelpResult(String itemName, String moneyGrant, String moneyDate, String issuingAgency,String  dataType) {
        this.itemName = itemName;
        this.moneyGrant = moneyGrant;
        this.moneyDate = moneyDate;
        this.issuingAgency = issuingAgency;
        this.dataType = dataType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMoneyGrant() {
        return moneyGrant;
    }

    public void setMoneyGrant(String moneyGrant) {
        this.moneyGrant = moneyGrant;
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

}
