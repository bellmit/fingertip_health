package com.jqsoft.fingertip_health.bean.fingertip;


import java.io.Serializable;

public class PersonPastHistoryBean implements Serializable {



     private String typeCode;
    private String pastHistoryCode;


    public PersonPastHistoryBean() {
    }

    public PersonPastHistoryBean(String typeCode, String pastHistoryCode) {
        this.typeCode = typeCode;
        this.pastHistoryCode = pastHistoryCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getPastHistoryCode() {
        return pastHistoryCode;
    }

    public void setPastHistoryCode(String pastHistoryCode) {
        this.pastHistoryCode = pastHistoryCode;
    }
}
