package com.jqsoft.fingertip_health.bean.fingertip;


import com.jqsoft.fingertip_health.bean.propMap;

import java.io.Serializable;
import java.util.List;

public class KuncunListActivityBean implements Serializable {
    private String organizationCode;
    private String departmentCode;
    private String drugId;
    private String stockId;
    private String stock;
    private String updateDate;
    private String expireDate;
    private String batchNumber;

    private String price;
    private String bid;
    private String remarks;
    private String priceSale;
    private String bidSale;
    private String available;

    private String stockView;
    private String trans;
    private String doseName;
    private String Name;
    private String priceView;

    private String supplier;
    private String drugSpecificationUnit;
    private  String approvalNumber;

    public KuncunListActivityBean() {
    }

    public KuncunListActivityBean(String organizationCode, String departmentCode, String drugId, String stockId, String stock, String updateDate, String expireDate, String batchNumber, String price, String bid, String remarks, String priceSale, String bidSale, String available,String supplier,String drugSpecificationUnit,String approvalNumber) {
        this.organizationCode = organizationCode;
        this.departmentCode = departmentCode;
        this.drugId = drugId;
        this.stockId = stockId;
        this.stock = stock;
        this.updateDate = updateDate;
        this.expireDate = expireDate;
        this.batchNumber = batchNumber;
        this.price = price;
        this.bid = bid;
        this.remarks = remarks;
        this.priceSale = priceSale;
        this.bidSale = bidSale;
        this.available = available;
        this.supplier = supplier;
        this.drugSpecificationUnit = drugSpecificationUnit;
        this.approvalNumber=approvalNumber;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;

    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    public String getBidSale() {
        return bidSale;
    }

    public void setBidSale(String bidSale) {
        this.bidSale = bidSale;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getStockView() {
        return stockView;
    }

    public void setStockView(String stockView) {
        this.stockView = stockView;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPriceView() {
        return priceView;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDrugSpecificationUnit() {
        return drugSpecificationUnit;
    }

    public void setDrugSpecificationUnit(String drugSpecificationUnit) {
        this.drugSpecificationUnit = drugSpecificationUnit;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }
}
