package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/10.
 */

public class TreatdirectoryBean implements Serializable {
    private String id;//	项目编码	String
    private String code;//	项目code	String
    private String name;//项目名称	String
    private String feeStandard;//收费标准	Integer
    private String type;//状态	Integer
    private String   feeUpperlimit	;//收费上限	Integer
    private String  feeLowerlimit;//	收费下限	Integer
    private String  feeUnit;//	收费单位	String
    private String   Reims;//	医保范围	String		保内  保外

    private int chargeFrequency = 1;

    public TreatdirectoryBean() {
    }

    public TreatdirectoryBean(String id, String code, String name, String feeStandard, String type, String feeUpperlimit, String feeLowerlimit, String feeUnit, String reims) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.feeStandard = feeStandard;
        this.type = type;
        this.feeUpperlimit = feeUpperlimit;
        this.feeLowerlimit = feeLowerlimit;
        this.feeUnit = feeUnit;
        Reims = reims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeeStandard() {
        return feeStandard;
    }

    public void setFeeStandard(String feeStandard) {
        this.feeStandard = feeStandard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeeUpperlimit() {
        return feeUpperlimit;
    }

    public void setFeeUpperlimit(String feeUpperlimit) {
        this.feeUpperlimit = feeUpperlimit;
    }

    public String getFeeLowerlimit() {
        return feeLowerlimit;
    }

    public void setFeeLowerlimit(String feeLowerlimit) {
        this.feeLowerlimit = feeLowerlimit;
    }

    public String getFeeUnit() {
        return feeUnit;
    }

    public void setFeeUnit(String feeUnit) {
        this.feeUnit = feeUnit;
    }

    public String getReims() {
        return Reims;
    }

    public void setReims(String reims) {
        Reims = reims;
    }

    public int getChargeFrequency() {
        return chargeFrequency;
    }

    public void setChargeFrequency(int chargeFrequency) {
        this.chargeFrequency = chargeFrequency;
    }
}
