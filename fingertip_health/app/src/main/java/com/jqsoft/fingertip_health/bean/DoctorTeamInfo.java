package com.jqsoft.fingertip_health.bean;

/**
 * Created by Jerry on 2017/8/22.
 */

public class DoctorTeamInfo {
    public static final String TEAM_LEADER_SIGN_YES = "1";
    public static final String TEAM_LEADER_SIGN_NO = "0";

    private String orgName;//                      签约机构名称
    private String teamName;//            签约团队名称
    private String doctorName;//            签约医生名称
    private String doctorPhone;//             签约医生联系方式
    private String docUserId;//           签约医生主键
    private String teamLeader;//             团队负责人（1 是 0否）

    public boolean isTeamLeader(){
        if (TEAM_LEADER_SIGN_YES.equals(teamLeader)){
            return true;
        } else {
            return false;
        }
    }

    public DoctorTeamInfo() {
    }

    public DoctorTeamInfo(String orgName, String teamName, String doctorName, String doctorPhone, String docUserId, String teamLeader) {
        this.orgName = orgName;
        this.teamName = teamName;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.docUserId = docUserId;
        this.teamLeader = teamLeader;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public String getDocUserId() {
        return docUserId;
    }

    public void setDocUserId(String docUserId) {
        this.docUserId = docUserId;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }
}
