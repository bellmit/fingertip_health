package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class ProgressDetailbean {
    private String cardNo;
    private String identifier;
    private String itemNames;
    private String moneyGrant;
    private String moneyStatue;
    private String name;
    private String state;
    private List<ResultOpinion> resultOpinion;

    public ProgressDetailbean() {
    }

    public ProgressDetailbean(String cardNo, String identifier, String itemNames, String moneyGrant, String moneyStatue, String name, String state, List<ResultOpinion> resultOpinion) {
        this.cardNo = cardNo;
        this.identifier = identifier;
        this.itemNames = itemNames;
        this.moneyGrant = moneyGrant;
        this.moneyStatue = moneyStatue;
        this.name = name;
        this.state = state;
        this.resultOpinion = resultOpinion;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }

    public String getMoneyGrant() {
        return moneyGrant;
    }

    public void setMoneyGrant(String moneyGrant) {
        this.moneyGrant = moneyGrant;
    }

    public String getMoneyStatue() {
        return moneyStatue;
    }

    public void setMoneyStatue(String moneyStatue) {
        this.moneyStatue = moneyStatue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ResultOpinion> getResultOpinion() {
        return resultOpinion;
    }

    public void setResultOpinion(List<ResultOpinion> resultOpinion) {
        this.resultOpinion = resultOpinion;
    }
}
