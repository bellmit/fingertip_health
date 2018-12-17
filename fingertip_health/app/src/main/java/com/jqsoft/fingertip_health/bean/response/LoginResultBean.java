package com.jqsoft.fingertip_health.bean.response;

import com.jqsoft.fingertip_health.bean.AreaBean;

import java.util.List;

/**
 * Created by Administrator on 2017-05-18.
 */

public class LoginResultBean {
    private String uid;             //用户ID
    private String username;    //登录名
    private String realname;    //真实姓名
    private String token;           //用户令牌
    private List<AreaBean> area;            //地区，如：“340000,340001”
    private String role;            //管理员

    public LoginResultBean() {
    }

    public LoginResultBean(String uid, String username, String realname, String token, List<AreaBean> area, String role) {
        this.uid = uid;
        this.username = username;
        this.realname = realname;
        this.token = token;
        this.area = area;
        this.role = role;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<AreaBean> getArea() {
        return area;
    }

    public void setArea(List<AreaBean> area) {
        this.area = area;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
