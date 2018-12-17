package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 *
 * 智能引导bean
 */

public class IgGuidePostBean implements Serializable {

    private String itemId;
    private String itemName;
    private String itemCode;
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }




    public IgGuidePostBean() {
        super();
    }
    public IgGuidePostBean(String itemId, String itemName, String itemCode) {
        this.itemId = itemId;
        this.itemName=  itemName;
        this.itemCode = itemCode;

    }










}
