package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/11.
 */

public class FeeDetailsCYInVo implements Serializable{
    private String serialNumber;//序号	Integer	N	开药顺序号，可以不连续，但顺序一定要从小到大排列
    private String drugId;//	药品编码	String	N
    private String price;//单价	Double
    private String usage;//	用法	String
    private String total;//总数	Double	N
    private String totalUnit;//总数单位	String	N
    private String totalAmount;//	总金额	Double	N
    private String stockId;//	库存流水号	String	N
    private String isReim;//医保范围	String	N	保内、保外

    public FeeDetailsCYInVo(String serialNumber, String drugId, String price, String usage, String total, String totalUnit, String totalAmount, String stockId, String isReim) {
        this.serialNumber = serialNumber;
        this.drugId = drugId;
        this.price = price;
        this.usage = usage;
        this.total = total;
        this.totalUnit = totalUnit;
        this.totalAmount = totalAmount;
        this.stockId = stockId;
        this.isReim = isReim;
    }

    public FeeDetailsCYInVo() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getIsReim() {
        return isReim;
    }

    public void setIsReim(String isReim) {
        this.isReim = isReim;
    }
}
