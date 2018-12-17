package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/9/4.
 */

public class PersonDoctorMessageInfo {
    private String docUserID;//             家庭医生主键
    private String doctorName;//          家庭医生名称
    private String doctorPhone;//         家庭医生手机号码
    private String message;//           最新的一条信息
    private String sendTime;//           发送的时间
    private String total;//           未读信息的数量
    private String sexName;
    private String photoUrl;
    private String age;



    public PersonDoctorMessageInfo() {
    }

    public PersonDoctorMessageInfo(String docUserID, String doctorName, String doctorPhone, String message, String sendTime, String total, String sexName, String photoUrl,String age) {
        this.docUserID = docUserID;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.message = message;
        this.sendTime = sendTime;
        this.total = total;
        this.sexName = sexName;
        this.photoUrl = photoUrl;
        this.age = age;

    }


    public String getDocUserID() {
        return docUserID;
    }

    public void setDocUserID(String docUserID) {
        this.docUserID = docUserID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
