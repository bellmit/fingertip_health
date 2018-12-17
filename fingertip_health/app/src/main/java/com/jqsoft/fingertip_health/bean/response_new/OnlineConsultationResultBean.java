package com.jqsoft.fingertip_health.bean.response_new;

/**
 * Created by Administrator on 2017-07-19.
 */

public class OnlineConsultationResultBean {
    private String postMessage;//信息
    private String setTime;//发送时间
    private String userName;//人员名称
    private String phone;//联系方式
    private String age;//年龄
    private boolean isDiabetes;
    private boolean isHypertension;
    private boolean isElderlyPeople;

    private String docUserId;//签约医生主键
    private String cardNo;//身份证
    private String shouldExecCount;//应该执行次数
    private String hadExecCount;//已经执行次数
    private String photoUrl;//照片url
    private String personMold;//人员类别
    private String serverPackageName;//服务包

    public OnlineConsultationResultBean() {
        super();
    }

    public OnlineConsultationResultBean(String postMessage, String setTime, String userName, String phone, String age, String docUserId, String cardNo, String shouldExecCount, String hadExecCount, String photoUrl, String personMold, String serverPackageName) {
        this.postMessage = postMessage;
        this.setTime = setTime;
        this.userName = userName;
        this.phone = phone;
        this.age = age;
        this.docUserId = docUserId;
        this.cardNo = cardNo;
        this.shouldExecCount = shouldExecCount;
        this.hadExecCount = hadExecCount;
        this.photoUrl = photoUrl;
        this.personMold = personMold;
        this.serverPackageName = serverPackageName;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean getIsDiabetes() {
        return isDiabetes;
    }

    public void setIsDiabetes(boolean diabetes) {
        isDiabetes = diabetes;
    }

    public boolean getIsHypertension() {
        return isHypertension;
    }

    public void setIsHypertension(boolean hypertension) {
        isHypertension = hypertension;
    }

    public boolean getIsElderlyPeople() {
        return isElderlyPeople;
    }

    public void setIsElderlyPeople(boolean elderlyPeople) {
        isElderlyPeople = elderlyPeople;
    }

    public String getDocUserId() {
        return docUserId;
    }

    public void setDocUserId(String docUserId) {
        this.docUserId = docUserId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getShouldExecCount() {
        return shouldExecCount;
    }

    public void setShouldExecCount(String shouldExecCount) {
        this.shouldExecCount = shouldExecCount;
    }

    public String getHadExecCount() {
        return hadExecCount;
    }

    public void setHadExecCount(String hadExecCount) {
        this.hadExecCount = hadExecCount;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPersonMold() {
        return personMold;
    }

    public void setPersonMold(String personMold) {
        this.personMold = personMold;
    }

    public String getServerPackageName() {
        return serverPackageName;
    }

    public void setServerPackageName(String serverPackageName) {
        this.serverPackageName = serverPackageName;
    }
}
