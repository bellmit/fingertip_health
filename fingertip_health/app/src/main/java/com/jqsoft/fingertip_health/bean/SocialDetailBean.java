package com.jqsoft.fingertip_health.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/29.
 */

public class SocialDetailBean {
    private DetailBaseInfo BaseInfo;
    private List<DetaiFamilMember> FamilyMember;
    private List<DetailUplodeFile> UploadFile;
    private DetailPoorBatch PoorBatch;
    private List<DetailHelpResult> HelpResult;
    private LocationMapBean mapMarker;


    public SocialDetailBean() {
    }


    public SocialDetailBean(DetailBaseInfo baseInfo, List<DetaiFamilMember> familyMember, List<DetailUplodeFile> uploadFile, DetailPoorBatch poorBatch, List<DetailHelpResult> helpResult, LocationMapBean mapMarker) {
        BaseInfo = baseInfo;
        FamilyMember = familyMember;
        UploadFile = uploadFile;
        PoorBatch = poorBatch;
        HelpResult = helpResult;
        this.mapMarker = mapMarker;
    }

    public DetailBaseInfo getBaseInfo() {
        return BaseInfo;
    }

    public void setBaseInfo(DetailBaseInfo baseInfo) {
        BaseInfo = baseInfo;
    }

    public List<DetaiFamilMember> getFamilyMember() {
        return FamilyMember;
    }

    public void setFamilyMember(List<DetaiFamilMember> familyMember) {
        FamilyMember = familyMember;
    }

    public List<DetailUplodeFile> getUploadFile() {
        return UploadFile;
    }

    public void setUploadFile(List<DetailUplodeFile> uploadFile) {
        UploadFile = uploadFile;
    }

    public DetailPoorBatch getPoorBatch() {
        return PoorBatch;
    }

    public void setPoorBatch(DetailPoorBatch poorBatch) {
        PoorBatch = poorBatch;
    }

    public List<DetailHelpResult> getHelpResult() {
        return HelpResult;
    }

    public void setHelpResult(List<DetailHelpResult> helpResult) {
        HelpResult = helpResult;
    }

    public LocationMapBean getMapMarker() {
        return mapMarker;
    }

    public void setMapMarker(LocationMapBean mapMarker) {
        this.mapMarker = mapMarker;
    }
}
