package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;
import java.util.List;

/**
 * 受理中心详情
 * Created by Administrator on 2018-01-18.
 */

public class ReceptionDetailBean implements Serializable {
    private String address;
    private String areaCode;
    private String areaLevel;
    private String editor;
    private String editDate;
    private String fileId;
    private String filePath;
    private String id;
    private String latitude;
    private String longitude;
    private String name;
    private String officeHours;
    private String state;
    private String telephone ;
    private String receptionId;
    private CollectionUrlBean collectionUrl;
    private  String collectionId;
    private  String collectioId;
    private  List<NewsListBean> newsList;

    public ReceptionDetailBean(String address, String areaCode, String areaLevel, String editor, String editDate, String fileId, String filePath, String id, String latitude, String longitude, String name, String officeHours, String state, String telephone, String receptionId, CollectionUrlBean collectionUrl, String collectionId, String collectioId, List<NewsListBean> newsList) {
        this.address = address;
        this.areaCode = areaCode;
        this.areaLevel = areaLevel;
        this.editor = editor;
        this.editDate = editDate;
        this.fileId = fileId;
        this.filePath = filePath;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.officeHours = officeHours;
        this.state = state;
        this.telephone = telephone;
        this.receptionId = receptionId;
        this.collectionUrl = collectionUrl;
        this.collectionId = collectionId;
        this.collectioId = collectioId;
        this.newsList = newsList;
    }

    public ReceptionDetailBean() {
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
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

    public String getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(String receptionId) {
        this.receptionId = receptionId;
    }

    public CollectionUrlBean getCollectionUrl() {
        return collectionUrl;
    }

    public void setCollectionUrl(CollectionUrlBean collectionUrl) {
        this.collectionUrl = collectionUrl;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectioId() {
        return collectioId;
    }

    public void setCollectioId(String collectioId) {
        this.collectioId = collectioId;
    }

    public List<NewsListBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsListBean> newsList) {
        this.newsList = newsList;
    }
}
