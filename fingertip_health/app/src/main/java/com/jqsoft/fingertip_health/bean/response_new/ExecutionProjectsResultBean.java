package com.jqsoft.fingertip_health.bean.response_new;

import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2017-07-03.
 */

public class ExecutionProjectsResultBean {
    private String photoUrl;     //头像url
    private int position;

    private String personID;
    private String personMold;
    private String signKey;
    private String year;
    private String userName;        //用户姓名
    private String cardNo;//                         身份证号码
    private String serverPackageName;//               服务包名称
    private String phone;//                           电话
    private String age;//年龄
    private String nhcompensateProjName;//包的等级
    private String shouldExecCount;//应该执行
    private String hadExecCount;//已经执行
    private PeopleBaseInfoBean signUserInfo = new PeopleBaseInfoBean();
    private List<ExecutionProjectsResultItemBean> list;  //项目列表
//    private List<String> name ;//                           项目名称
//    private List<String> nxetdate ;//                        下次执行时间
//    private String signDetailID;//                      服务明细表主键
//    private String serviceContentID ;//                 服务包主键
//    private String serviceContentItemsID ;//            服务项目主键

    public ExecutionProjectsResultBean() {
        super();
    }

    public ExecutionProjectsResultBean(String photoUrl, int position, String personID, String personMold, String signKey, String year, String userName, String cardNo, String serverPackageName, String phone, String age, String nhcompensateProjName, String shouldExecCount, String hadExecCount, PeopleBaseInfoBean signUserInfo, List<ExecutionProjectsResultItemBean> list) {
        this.photoUrl = photoUrl;
        this.position = position;
        this.personID = personID;
        this.personMold = personMold;
        this.signKey = signKey;
        this.year = year;
        this.userName = userName;
        this.cardNo = cardNo;
        this.serverPackageName = serverPackageName;
        this.phone = phone;
        this.age = age;
        this.nhcompensateProjName = nhcompensateProjName;
        this.shouldExecCount = shouldExecCount;
        this.hadExecCount = hadExecCount;
        this.signUserInfo = signUserInfo;
        this.list = list;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getServerPackageName() {
        return serverPackageName;
    }

    public void setServerPackageName(String serverPackageName) {
        this.serverPackageName = serverPackageName;
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

    public String getNhcompensateProjName() {
        return nhcompensateProjName;
    }

    public void setNhcompensateProjName(String nhcompensateProjName) {
        this.nhcompensateProjName = nhcompensateProjName;
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

    public List<ExecutionProjectsResultItemBean> getList() {
        return list;
    }

    public void setList(List<ExecutionProjectsResultItemBean> list) {
        this.list = list;
    }

    public PeopleBaseInfoBean getSignUserInfo() {
        return signUserInfo;
    }

    public void setSignUserInfo(PeopleBaseInfoBean signUserInfo) {
        this.signUserInfo = signUserInfo;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonMold() {
        return personMold;
    }

    public void setPersonMold(String personMold) {
        this.personMold = personMold;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
