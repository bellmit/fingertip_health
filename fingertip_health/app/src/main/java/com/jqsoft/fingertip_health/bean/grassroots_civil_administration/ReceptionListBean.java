package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;

/**
 * 受理中心列表
 * Created by Administrator on 2017-12-27.
 */

public class ReceptionListBean implements Serializable {
    private String address ;
    private String areaCode;
    private String areaLevel;
    private String editDate;
    private String editor ;
    private String fileId;
    private String filePath;
    private String id;
    private String latitude;
    private String longitude;
    private String name;
    private String officeHours;
    private String pareaCode;
    private String receptionId;
    private String state;
    private String telephone;
    private CollectionUrlBean collectionUrl;

    public CollectionUrlBean getCollectionUrl() {
        return collectionUrl;
    }

    public void setCollectionUrl(CollectionUrlBean collectionUrl) {
        this.collectionUrl = collectionUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getPareaCode() {
        return pareaCode;
    }

    public void setPareaCode(String pareaCode) {
        this.pareaCode = pareaCode;
    }

    public String getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(String receptionId) {
        this.receptionId = receptionId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }




    public ReceptionListBean() {
        super();
    }

    public ReceptionListBean(String address, String areaCode, String areaLevel,
                             String editDate, String editor, String fileId,
                             String filePath, String id, String latitude,
                             String longitude, String name, String officeHours,
                             String pareaCode, String receptionId, String state,
                             String telephone

                             ) {
        this.address = address;
        this.areaCode=  areaCode;
        this.areaLevel = areaLevel;
        this.editDate = editDate;
        this.editor = editor;
        this.fileId=  fileId;
        this.filePath = filePath;
        this.id = id;
        this.latitude = latitude;
        this.longitude=  longitude;
        this.name = name;
        this.officeHours = officeHours;
        this.pareaCode = pareaCode;
        this.receptionId=  receptionId;
        this.state = state;
        this.telephone = telephone;



    }


}
