package com.jqsoft.fingertip_health.bean.fingertip;


import com.jqsoft.fingertip_health.bean.propMap;

import java.io.Serializable;
import java.util.List;

public class PersonallergyHistoriesBean implements Serializable {



     private String allergyHistoryCode;

    public String getAllergyHistoryCode() {
        return allergyHistoryCode;
    }

    public void setAllergyHistoryCode(String allergyHistoryCode) {
        this.allergyHistoryCode = allergyHistoryCode;
    }

    public PersonallergyHistoriesBean() {
    }

    public PersonallergyHistoriesBean(String allergyHistoryCode) {
        this.allergyHistoryCode = allergyHistoryCode;
    }
}
