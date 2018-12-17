package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

public class ReservationBeanList implements Serializable{
    private String address;
    private String age;
    private String cardNo;
    private String docUserId;
    private String doctorName;
    private String no;
    private String pakageName;
    private String personMold;
    private String phone;
    private String photoUrl;
    private String reservationServrtPlace;
    private String reservationServrtPlaceOther;
    private String reservationTime;//拒绝执行次数
    private String serverContent;
    private String serviceContentID;
    private String serviceContentItemsID;
    private String sexCode;
    private String signDetailID;//拒绝执行次数
    private String userName;
    private String reservationState;
    private String servicePlanID;
    private String serverTime;

    private PeopleBaseInfoBean signUserInfo = new PeopleBaseInfoBean();

    private PendExecuBeanList signPromExec = new PendExecuBeanList();

    public ReservationBeanList() {
    }

    public ReservationBeanList(String address, String age, String cardNo, String docUserId, String doctorName, String no, String pakageName, String personMold, String phone, String photoUrl, String reservationServrtPlace, String reservationServrtPlaceOther, String reservationTime, String serverContent, String serviceContentID, String serviceContentItemsID, String sexCode, String signDetailID, String userName, String reservationState) {
        this.address = address;
        this.age = age;
        this.cardNo = cardNo;
        this.docUserId = docUserId;
        this.doctorName = doctorName;
        this.no = no;
        this.pakageName = pakageName;
        this.personMold = personMold;
        this.phone = phone;
        this.photoUrl = photoUrl;
        this.reservationServrtPlace = reservationServrtPlace;
        this.reservationServrtPlaceOther = reservationServrtPlaceOther;
        this.reservationTime = reservationTime;
        this.serverContent = serverContent;
        this.serviceContentID = serviceContentID;
        this.serviceContentItemsID = serviceContentItemsID;
        this.sexCode = sexCode;
        this.signDetailID = signDetailID;
        this.userName = userName;
        this.reservationState = reservationState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDocUserId() {
        return docUserId;
    }

    public void setDocUserId(String docUserId) {
        this.docUserId = docUserId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPakageName() {
        return pakageName;
    }

    public void setPakageName(String pakageName) {
        this.pakageName = pakageName;
    }

    public String getPersonMold() {
        return personMold;
    }

    public void setPersonMold(String personMold) {
        this.personMold = personMold;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getReservationServrtPlace() {
        return reservationServrtPlace;
    }

    public void setReservationServrtPlace(String reservationServrtPlace) {
        this.reservationServrtPlace = reservationServrtPlace;
    }

    public String getReservationServrtPlaceOther() {
        return reservationServrtPlaceOther;
    }

    public void setReservationServrtPlaceOther(String reservationServrtPlaceOther) {
        this.reservationServrtPlaceOther = reservationServrtPlaceOther;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getServerContent() {
        return serverContent;
    }

    public void setServerContent(String serverContent) {
        this.serverContent = serverContent;
    }

    public String getServiceContentID() {
        return serviceContentID;
    }

    public void setServiceContentID(String serviceContentID) {
        this.serviceContentID = serviceContentID;
    }

    public String getServiceContentItemsID() {
        return serviceContentItemsID;
    }

    public void setServiceContentItemsID(String serviceContentItemsID) {
        this.serviceContentItemsID = serviceContentItemsID;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getSignDetailID() {
        return signDetailID;
    }

    public void setSignDetailID(String signDetailID) {
        this.signDetailID = signDetailID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public PeopleBaseInfoBean getSignUserInfo() {
        return signUserInfo;
    }

    public void setSignUserInfo(PeopleBaseInfoBean signUserInfo) {
        this.signUserInfo = signUserInfo;
    }

    public PendExecuBeanList getSignPromExec() {
        return signPromExec;
    }

    public void setSignPromExec(PendExecuBeanList signPromExec) {
        this.signPromExec = signPromExec;
    }

    public String getServicePlanID() {
        return servicePlanID;
    }

    public void setServicePlanID(String servicePlanID) {
        this.servicePlanID = servicePlanID;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }
}
