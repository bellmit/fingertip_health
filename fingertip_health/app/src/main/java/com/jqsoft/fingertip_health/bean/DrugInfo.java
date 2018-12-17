package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/10.
 */

public class DrugInfo implements Serializable {
    private String id;//	编码	String
    private String name;//产品名称	String
    private String spec;//	规格	String
    private String dose;//字典项名称	String
    private String stock;//	可用库存数量	Integer
    private String price;//	小单位零价	Integer
    private String supplier;//	生产企业	String
    private String day;//到期日期	String
    private String no;//批号	String
    private String alias;//	其他名称	String
    private String unit;//	字典项名称	String
    private String dosetype;//剂型	String
    private String type;//类别（字典项）	String
    private String unitYkName;//	字典项名称	String
    private String trans;//	转换系统	Integer
    private String usage;//	常规用法（字典项）	String
    private String frequency;//	常规频次	String
    private String times;//	备注	String
    private String doseUnit;//	字典项名称	String
    private String packetDose;//小包剂量	Integer
    private String conventionalQuantity;//	常规数量	Integer
    private String priceSale;//大单位零价	Integer
    private String stockId;//	库存流水号	String
    private String mpu;//最小包装单位标志 	Integer
    private String typeBasicDrug;//	基药分类	Integer
    private String cgts;//常规天数	Integer
    private String isReim;//标识	String
    private String textureMaterial;//材质	String

   private  String tempPrice;//此价格作为总价计算  每次默认赋值或者选择价格时赋值
    private  String tempUnit;//默认赋值或者更改单位后赋值
    private  String  tempMpu;

    private int chargeFrequency=1;
    public DrugInfo() {
    }

    public DrugInfo(String id, String name, String spec, String dose, String stock, String price, String supplier, String day, String no, String alias, String unit, String dosetype, String type, String unitYkName, String trans, String usage, String frequency, String times, String doseUnit, String packetDose, String conventionalQuantity, String priceSale, String stockId, String mpu, String typeBasicDrug, String cgts, String isReim, String textureMaterial,String tempPrice,String tempUnit,String tempMpu) {
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.dose = dose;
        this.stock = stock;
        this.price = price;
        this.supplier = supplier;
        this.day = day;
        this.no = no;
        this.alias = alias;
        this.unit = unit;
        this.dosetype = dosetype;
        this.type = type;
        this.unitYkName = unitYkName;
        this.trans = trans;
        this.usage = usage;
        this.frequency = frequency;
        this.times = times;
        this.doseUnit = doseUnit;
        this.packetDose = packetDose;
        this.conventionalQuantity = conventionalQuantity;
        this.priceSale = priceSale;
        this.stockId = stockId;
        this.mpu = mpu;
        this.typeBasicDrug = typeBasicDrug;
        this.cgts = cgts;
        this.isReim = isReim;
        this.textureMaterial = textureMaterial;
        this.tempPrice = tempPrice;
        this.tempUnit = tempUnit;
        this.tempMpu= tempMpu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDosetype() {
        return dosetype;
    }

    public void setDosetype(String dosetype) {
        this.dosetype = dosetype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitYkName() {
        return unitYkName;
    }

    public void setUnitYkName(String unitYkName) {
        this.unitYkName = unitYkName;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit;
    }

    public String getPacketDose() {
        return packetDose;
    }

    public void setPacketDose(String packetDose) {
        this.packetDose = packetDose;
    }

    public String getConventionalQuantity() {
        return conventionalQuantity;
    }

    public void setConventionalQuantity(String conventionalQuantity) {
        this.conventionalQuantity = conventionalQuantity;
    }

    public String getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getMpu() {
        return mpu;
    }

    public void setMpu(String mpu) {
        this.mpu = mpu;
    }

    public String getTypeBasicDrug() {
        return typeBasicDrug;
    }

    public void setTypeBasicDrug(String typeBasicDrug) {
        this.typeBasicDrug = typeBasicDrug;
    }

    public String getCgts() {
        return cgts;
    }

    public void setCgts(String cgts) {
        this.cgts = cgts;
    }

    public String getIsReim() {
        return isReim;
    }

    public void setIsReim(String isReim) {
        this.isReim = isReim;
    }

    public String getTextureMaterial() {
        return textureMaterial;
    }

    public void setTextureMaterial(String textureMaterial) {
        this.textureMaterial = textureMaterial;
    }

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    public String getTempPrice() {
        return tempPrice;
    }

    public String getTempMpu() {
        return tempMpu;
    }

    public void setTempMpu(String tempMpu) {
        this.tempMpu = tempMpu;
    }

    public void setTempPrice(String tempPrice) {
        this.tempPrice = tempPrice;
    }

    public int getChargeFrequency() {
        return chargeFrequency;
    }

    public void setChargeFrequency(int chargeFrequency) {
        this.chargeFrequency = chargeFrequency;
    }
}
