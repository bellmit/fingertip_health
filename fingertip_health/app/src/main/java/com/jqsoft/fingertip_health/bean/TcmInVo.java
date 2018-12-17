package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/11.
 */

public class TcmInVo implements Serializable {
    private String everydayDose;
    private String everydayTime;
    private String days;
    private String icdCode;
    private String icdName;
    private String payNumber;
    private String rise;//
    private String remark;//每日剂量
    private ArrayList<FeeDetailsCYInVo>feeDetailsInVo = new ArrayList();

    public TcmInVo() {
    }

    public TcmInVo(String everydayDose, String everydayTime, String days, String icdCode, String icdName, String payNumber, String rise, String remark, ArrayList<FeeDetailsCYInVo> feeDetailsInVo) {
        this.everydayDose = everydayDose;
        this.everydayTime = everydayTime;
        this.days = days;
        this.icdCode = icdCode;
        this.icdName = icdName;
        this.payNumber = payNumber;
        this.rise = rise;
        this.remark = remark;
        this.feeDetailsInVo = feeDetailsInVo;
    }

    public String getEverydayDose() {
        return everydayDose;
    }

    public void setEverydayDose(String everydayDose) {
        this.everydayDose = everydayDose;
    }

    public String getEverydayTime() {
        return everydayTime;
    }

    public void setEverydayTime(String everydayTime) {
        this.everydayTime = everydayTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getIcdName() {
        return icdName;
    }

    public void setIcdName(String icdName) {
        this.icdName = icdName;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<FeeDetailsCYInVo> getFeeDetailsInVo() {
        return feeDetailsInVo;
    }

    public void setFeeDetailsInVo(ArrayList<FeeDetailsCYInVo> feeDetailsInVo) {
        this.feeDetailsInVo = feeDetailsInVo;
    }
}
