package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Mars on 2018/3/30.
 */

public class AdviceDetailBean {

    private String conNo;
    private  String replyTime;
    private String replyUnit;
    private  String replyUnitCode;
    private  String replyContent;

    //咨询机构
    private String unitCode;
    //咨询建议类型
    private String type;
    //标题
    private String title;
    //咨询人姓名
    private String name;
    //电话
    private String telphone;
    //邮箱
    private String email;
    //咨询时间
    private String consultTime;
    //咨询内容
    private String content;
    //是否回复（0未回复，1转办，2是追问，3已回复）
    private String isReply;
    //标识（0正常，9 删除）
    private String flag;
    //省级机构
    private String pUnitCode;
    //市级机构
    private String cUnitCode;
    //区县级机构
    private String coUnitCode;
    //乡镇级机构
    private String oUnitCode;
    //社区级机构
    private String comUnitCode;
    /** 机构名称 */
    private String unitConsultName;

    private List<ConsultChildrenBean> consultChildren;
    private List<RepliesBean> replies;

    public AdviceDetailBean() {
    }

    public AdviceDetailBean(String isReply, String conNo, String content, String consultTime, String replyTime, String replyUnit, String replyUnitCode, String replyContent, String title, List<ConsultChildrenBean> consultChildren, List<RepliesBean> replies) {
        this.isReply = isReply;
        this.conNo = conNo;
        this.content = content;
        this.consultTime = consultTime;
        this.replyTime = replyTime;
        this.replyUnit = replyUnit;
        this.replyUnitCode = replyUnitCode;
        this.replyContent = replyContent;
        this.title = title;
        this.consultChildren = consultChildren;
        this.replies = replies;
    }

    public String getIsReply() {
        return isReply;
    }

    public void setIsReply(String isReply) {
        this.isReply = isReply;
    }

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(String consultTime) {
        this.consultTime = consultTime;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyUnit() {
        return replyUnit;
    }

    public void setReplyUnit(String replyUnit) {
        this.replyUnit = replyUnit;
    }

    public String getReplyUnitCode() {
        return replyUnitCode;
    }

    public void setReplyUnitCode(String replyUnitCode) {
        this.replyUnitCode = replyUnitCode;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ConsultChildrenBean> getConsultChildren() {
        return consultChildren;
    }

    public void setConsultChildren(List<ConsultChildrenBean> consultChildren) {
        this.consultChildren = consultChildren;
    }

    public List<RepliesBean> getReplies() {
        return replies;
    }

    public void setReplies(List<RepliesBean> replies) {
        this.replies = replies;
    }


    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getpUnitCode() {
        return pUnitCode;
    }

    public void setpUnitCode(String pUnitCode) {
        this.pUnitCode = pUnitCode;
    }

    public String getcUnitCode() {
        return cUnitCode;
    }

    public void setcUnitCode(String cUnitCode) {
        this.cUnitCode = cUnitCode;
    }

    public String getCoUnitCode() {
        return coUnitCode;
    }

    public void setCoUnitCode(String coUnitCode) {
        this.coUnitCode = coUnitCode;
    }

    public String getoUnitCode() {
        return oUnitCode;
    }

    public void setoUnitCode(String oUnitCode) {
        this.oUnitCode = oUnitCode;
    }

    public String getComUnitCode() {
        return comUnitCode;
    }

    public void setComUnitCode(String comUnitCode) {
        this.comUnitCode = comUnitCode;
    }

    public String getUnitConsultName() {
        return unitConsultName;
    }

    public void setUnitConsultName(String unitConsultName) {
        this.unitConsultName = unitConsultName;
    }
}
