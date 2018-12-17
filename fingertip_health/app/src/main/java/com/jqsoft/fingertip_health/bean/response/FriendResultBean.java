package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-08.
 */

public class FriendResultBean {
    private String uid;
    private String username;
    private String nickname;
    private String realName;
    private String area;
    private String mobile;

    public FriendResultBean() {
    }

    public FriendResultBean(String uid, String username, String nickname, String realName, String area, String mobile) {
        this.uid = uid;
        this.username = username;
        this.nickname = nickname;
        this.realName=realName;
        this.area = area;
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
