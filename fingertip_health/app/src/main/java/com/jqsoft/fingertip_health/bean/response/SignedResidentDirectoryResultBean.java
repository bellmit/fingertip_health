package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-26.
 */

public class SignedResidentDirectoryResultBean {
    private String address;//                  居民地址
    private String countNum;//                    总数
    private String phone;//                       居民电话号码
    private String  userName;//                    居民姓名
    public SignedResidentDirectoryResultBean() {
        super();
    }

    public SignedResidentDirectoryResultBean(String address, String countNum, String phone, String userName) {
        this.address = address;
        this.countNum = countNum;
        this.phone = phone;
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountNum() {
        return countNum;
    }

    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
