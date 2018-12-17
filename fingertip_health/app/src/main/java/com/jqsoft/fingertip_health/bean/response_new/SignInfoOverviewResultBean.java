package com.jqsoft.fingertip_health.bean.response_new;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-06-29.
 */

//签约情况概览
public class SignInfoOverviewResultBean implements Serializable {
    private String type;   //1,
    private String year;   //年份，
    private String qyCount;   //签约人数，
    private String qyPaidCount ;   //签约有偿人数，
    private String qyBasisCount;   //签约基础包人数,
    private String qyOldCount;   //签约老年人数,
    private String qyGxyCount;   //签约高血压人数,
    private String qyTnbCount;   //签约糖尿病人数,
    private String qyPaidLv;   //有偿签约率,
    private String qyOldLv;   //老年人签约率，
    private String qyGxyLv;   //高血压签约率,
    private String qyTnbLv;   //糖尿病签约率,
    private String qyJuniorPack;   //初级包数,
    private String qyIntermediatePack;   //中级包数,
    private String qyAdvancedPack;   //高级包数,
    private String qyPaidPack;   //有偿签约包数

    private String starSum;//星级
    public SignInfoOverviewResultBean() {
        super();
    }

    public SignInfoOverviewResultBean(String type, String year, String qyCount, String qyPaidCount, String qyBasisCount, String qyOldCount, String qyGxyCount, String qyTnbCount, String qyPaidLv, String qyOldLv, String qyGxyLv, String qyTnbLv, String qyJuniorPack, String qyIntermediatePack, String qyAdvancedPack, String qyPaidPack) {
        this.type = type;
        this.year = year;
        this.qyCount = qyCount;
        this.qyPaidCount = qyPaidCount;
        this.qyBasisCount = qyBasisCount;
        this.qyOldCount = qyOldCount;
        this.qyGxyCount = qyGxyCount;
        this.qyTnbCount = qyTnbCount;
        this.qyPaidLv = qyPaidLv;
        this.qyOldLv = qyOldLv;
        this.qyGxyLv = qyGxyLv;
        this.qyTnbLv = qyTnbLv;
        this.qyJuniorPack = qyJuniorPack;
        this.qyIntermediatePack = qyIntermediatePack;
        this.qyAdvancedPack = qyAdvancedPack;
        this.qyPaidPack = qyPaidPack;
    }

    public SignInfoOverviewResultBean(String type, String year, String qyCount, String qyPaidCount, String qyBasisCount, String qyOldCount, String qyGxyCount, String qyTnbCount, String qyPaidLv, String qyOldLv, String qyGxyLv, String qyTnbLv, String qyJuniorPack, String qyIntermediatePack, String qyAdvancedPack, String qyPaidPack, String starSum) {
        this.type = type;
        this.year = year;
        this.qyCount = qyCount;
        this.qyPaidCount = qyPaidCount;
        this.qyBasisCount = qyBasisCount;
        this.qyOldCount = qyOldCount;
        this.qyGxyCount = qyGxyCount;
        this.qyTnbCount = qyTnbCount;
        this.qyPaidLv = qyPaidLv;
        this.qyOldLv = qyOldLv;
        this.qyGxyLv = qyGxyLv;
        this.qyTnbLv = qyTnbLv;
        this.qyJuniorPack = qyJuniorPack;
        this.qyIntermediatePack = qyIntermediatePack;
        this.qyAdvancedPack = qyAdvancedPack;
        this.qyPaidPack = qyPaidPack;
        this.starSum = starSum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQyCount() {
        return qyCount;
    }

    public void setQyCount(String qyCount) {
        this.qyCount = qyCount;
    }

    public String getQyPaidCount() {
        return qyPaidCount;
    }

    public void setQyPaidCount(String qyPaidCount) {
        this.qyPaidCount = qyPaidCount;
    }

    public String getQyBasisCount() {
        return qyBasisCount;
    }

    public void setQyBasisCount(String qyBasisCount) {
        this.qyBasisCount = qyBasisCount;
    }

    public String getQyOldCount() {
        return qyOldCount;
    }

    public void setQyOldCount(String qyOldCount) {
        this.qyOldCount = qyOldCount;
    }

    public String getQyGxyCount() {
        return qyGxyCount;
    }

    public void setQyGxyCount(String qyGxyCount) {
        this.qyGxyCount = qyGxyCount;
    }

    public String getQyTnbCount() {
        return qyTnbCount;
    }

    public void setQyTnbCount(String qyTnbCount) {
        this.qyTnbCount = qyTnbCount;
    }

    public String getQyPaidLv() {
        return qyPaidLv;
    }

    public void setQyPaidLv(String qyPaidLv) {
        this.qyPaidLv = qyPaidLv;
    }

    public String getQyOldLv() {
        return qyOldLv;
    }

    public void setQyOldLv(String qyOldLv) {
        this.qyOldLv = qyOldLv;
    }

    public String getQyGxyLv() {
        return qyGxyLv;
    }

    public void setQyGxyLv(String qyGxyLv) {
        this.qyGxyLv = qyGxyLv;
    }

    public String getQyTnbLv() {
        return qyTnbLv;
    }

    public void setQyTnbLv(String qyTnbLv) {
        this.qyTnbLv = qyTnbLv;
    }

    public String getQyJuniorPack() {
        return qyJuniorPack;
    }

    public void setQyJuniorPack(String qyJuniorPack) {
        this.qyJuniorPack = qyJuniorPack;
    }

    public String getQyIntermediatePack() {
        return qyIntermediatePack;
    }

    public void setQyIntermediatePack(String qyIntermediatePack) {
        this.qyIntermediatePack = qyIntermediatePack;
    }

    public String getQyAdvancedPack() {
        return qyAdvancedPack;
    }

    public void setQyAdvancedPack(String qyAdvancedPack) {
        this.qyAdvancedPack = qyAdvancedPack;
    }

    public String getQyPaidPack() {
        return qyPaidPack;
    }

    public void setQyPaidPack(String qyPaidPack) {
        this.qyPaidPack = qyPaidPack;
    }

    public String getStarSum() {
        return starSum;
    }

    public void setStarSum(String starSum) {
        this.starSum = starSum;
    }
}
