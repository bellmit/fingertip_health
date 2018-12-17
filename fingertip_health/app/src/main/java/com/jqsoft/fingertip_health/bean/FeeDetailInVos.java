package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/11.
 */

public class FeeDetailInVos implements Serializable{
    private String serialNumber;//序号	Integer	N	开药顺序号，可以不连续，但顺序一定要从小到大排列
    private String drugId;//药品编码	String	N
    private String price;//单价	Double
    private String single;//	单次	Integer
    private String usage;//用法名称	String		注意用法传入名称【DRUG_USAGE】
    private String frequency;//	频次	String		频次代码【DRUG_FREQUENCY】
    private String days;//天数	Integer
    private String byDate;//截至日期	String		yyyyMMdd (当天日期+天数)
    private String total;//总数	Double	N
    private String totalUnit;//	总数单位	String	N
    private String skinTest;//皮试	Integer		0=否;1=是  或不传
    private String totalAmount;//	总金额	Double	N
    private String stockId;//库存流水号	String	N
    private String isReim;//医保范围	String	N	保内、保外
    private String useQuantity;//	用量	Double
    private String mpu;
    public FeeDetailInVos() {
    }

    public FeeDetailInVos(String serialNumber, String drugId, String price, String single, String usage, String frequency, String days, String byDate, String total, String totalUnit, String skinTest, String totalAmount, String stockId, String isReim, String useQuantity,String mpu) {
        this.serialNumber = serialNumber;
        this.drugId = drugId;
        this.price = price;
        this.single = single;
        this.usage = usage;
        this.frequency = frequency;
        this.days = days;
        this.byDate = byDate;
        this.total = total;
        this.totalUnit = totalUnit;
        this.skinTest = skinTest;
        this.totalAmount = totalAmount;
        this.stockId = stockId;
        this.isReim = isReim;
        this.useQuantity = useQuantity;
        this.mpu = mpu;
    }

    public String getMpu() {
        return mpu;
    }

    public void setMpu(String mpu) {
        this.mpu = mpu;
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

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getByDate() {
        return byDate;
    }

    public void setByDate(String byDate) {
        this.byDate = byDate;
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

    public String getSkinTest() {
        return skinTest;
    }

    public void setSkinTest(String skinTest) {
        this.skinTest = skinTest;
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

    public String getUseQuantity() {
        return useQuantity;
    }

    public void setUseQuantity(String useQuantity) {
        this.useQuantity = useQuantity;
    }
}
