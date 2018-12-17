package com.jqsoft.fingertip_health.bean.response_new;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-10.
 */
//签约服务收入bean
public class SignServiceIncomeResultBean implements Serializable {
    private String serviceClassSort;//(2 初级包   3 中级包   4高级包)
    private String qyrs;//签约人数
    private String packSumFee;//总收入
    private String shouldSelfFee;//自付金额
    private String newRuralCMSFee;//新农合（医保）金额
    private String basicPubilcMoney;//公共卫生承担金额
    private String otherReduceFee;//减免金额
    private String actualPackageSumFee;//实收金额
    public SignServiceIncomeResultBean() {
        super();
    }

    public SignServiceIncomeResultBean(String serviceClassSort, String qyrs, String packSumFee, String shouldSelfFee, String newRuralCMSFee, String basicPubilcMoney, String otherReduceFee, String actualPackageSumFee) {
        this.serviceClassSort = serviceClassSort;
        this.qyrs = qyrs;
        this.packSumFee = packSumFee;
        this.shouldSelfFee = shouldSelfFee;
        this.newRuralCMSFee = newRuralCMSFee;
        this.basicPubilcMoney = basicPubilcMoney;
        this.otherReduceFee = otherReduceFee;
        this.actualPackageSumFee = actualPackageSumFee;
    }

    public String getServiceClassSort() {
        return serviceClassSort;
    }

    public void setServiceClassSort(String serviceClassSort) {
        this.serviceClassSort = serviceClassSort;
    }

    public String getQyrs() {
        return qyrs;
    }

    public void setQyrs(String qyrs) {
        this.qyrs = qyrs;
    }

    public String getPackSumFee() {
        return packSumFee;
    }

    public void setPackSumFee(String packSumFee) {
        this.packSumFee = packSumFee;
    }

    public String getShouldSelfFee() {
        return shouldSelfFee;
    }

    public void setShouldSelfFee(String shouldSelfFee) {
        this.shouldSelfFee = shouldSelfFee;
    }

    public String getNewRuralCMSFee() {
        return newRuralCMSFee;
    }

    public void setNewRuralCMSFee(String newRuralCMSFee) {
        this.newRuralCMSFee = newRuralCMSFee;
    }

    public String getBasicPubilcMoney() {
        return basicPubilcMoney;
    }

    public void setBasicPubilcMoney(String basicPubilcMoney) {
        this.basicPubilcMoney = basicPubilcMoney;
    }

    public String getOtherReduceFee() {
        return otherReduceFee;
    }

    public void setOtherReduceFee(String otherReduceFee) {
        this.otherReduceFee = otherReduceFee;
    }

    public String getActualPackageSumFee() {
        return actualPackageSumFee;
    }

    public void setActualPackageSumFee(String actualPackageSumFee) {
        this.actualPackageSumFee = actualPackageSumFee;
    }
}
