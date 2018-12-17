package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * 指南详情
 * Created by Administrator on 2017-12-27.
 */

public class ReliefItemBean implements Serializable {
    private String collectionId;
    private String itemName;
    private String applyMaterial;
    private String acceptCondition;
    private String acceptDepartment;
    private String advice;
    private String areaId;
    private String areaName;
    private String editDate;
    private String editor;
    private String feeStandard;
    private String handleFlow;
    private String itemId;
    private String overDay;

    public String getCollectioId() {
        return collectioId;
    }

    public void setCollectioId(String collectioId) {
        this.collectioId = collectioId;
    }

    private String collectioId;

    public ReliefItemBean(String collectionId, String itemName, String applyMaterial, String acceptCondition, String acceptDepartment, String advice, String areaId, String areaName, String editDate, String editor, String feeStandard, String handleFlow, String itemId, String overDay, String collectioId, String policyBasis, String unitId, String collectionUrl) {
        this.collectionId = collectionId;
        this.itemName = itemName;
        this.applyMaterial = applyMaterial;
        this.acceptCondition = acceptCondition;
        this.acceptDepartment = acceptDepartment;
        this.advice = advice;
        this.areaId = areaId;
        this.areaName = areaName;
        this.editDate = editDate;
        this.editor = editor;
        this.feeStandard = feeStandard;
        this.handleFlow = handleFlow;
        this.itemId = itemId;
        this.overDay = overDay;
        this.collectioId = collectioId;
        this.policyBasis = policyBasis;
        this.unitId = unitId;
        this.collectionUrl = collectionUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    private String policyBasis;
    private String unitId;
    private String  collectionUrl;


    public String getCollectionId() {
        return collectionId;
    }



    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionUrl() {
        return collectionUrl;
    }

    public void setCollectionUrl(String collectionUrl) {
        this.collectionUrl = collectionUrl;
    }

    public ReliefItemBean() {
        super();
    }


    public String getApplyMaterial() {
        return applyMaterial;
    }

    public void setApplyMaterial(String applyMaterial) {
        this.applyMaterial = applyMaterial;
    }

    public String getAcceptCondition() {
        return acceptCondition;
    }

    public void setAcceptCondition(String acceptCondition) {
        this.acceptCondition = acceptCondition;
    }

    public String getAcceptDepartment() {
        return acceptDepartment;
    }

    public void setAcceptDepartment(String acceptDepartment) {
        this.acceptDepartment = acceptDepartment;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getFeeStandard() {
        return feeStandard;
    }

    public void setFeeStandard(String feeStandard) {
        this.feeStandard = feeStandard;
    }

    public String getHandleFlow() {
        return handleFlow;
    }

    public void setHandleFlow(String handleFlow) {
        this.handleFlow = handleFlow;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOverDay() {
        return overDay;
    }

    public void setOverDay(String overDay) {
        this.overDay = overDay;
    }

    public String getPolicyBasis() {
        return policyBasis;
    }

    public void setPolicyBasis(String policyBasis) {
        this.policyBasis = policyBasis;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }




}
