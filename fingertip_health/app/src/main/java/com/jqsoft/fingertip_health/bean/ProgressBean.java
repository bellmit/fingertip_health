package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Jerry on 2018/1/8.
 */

public class ProgressBean implements Serializable{
    private String identifier;
    private String name;
    private String cardNo;
    private String itemNames;
    private String itemCode;
    private String state;
    private String finishTime;

    public ProgressBean() {
    }


    public ProgressBean(String identifier, String name, String cardNo, String itemNames, String itemCode, String state, String finishTime) {
        this.identifier = identifier;
        this.name = name;
        this.cardNo = cardNo;
        this.itemNames = itemNames;
        this.itemCode = itemCode;
        this.state = state;
        this.finishTime = finishTime;

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
