package com.jqsoft.fingertip_health.bean.fingertip;


import com.jqsoft.fingertip_health.bean.propMap;

import java.io.Serializable;
import java.util.List;

public class PersonListActivityBean implements Serializable {



     private String id;
     private String personId;
     private String no;
     private String jtNo;
     private String name;
     private String sexName;
     private String sexCode;
     private String age;
     private String idNo;
     private String contactPhone;
     private String signState;
     private String archivingDoctorDate;
     private String areaFulladdress;
     private String signOrganizationCode;
     private String areaId;
    private List<propMap> propMap;

    public PersonListActivityBean() {
    }

    public PersonListActivityBean(String id, String personId, String no, String jtNo, String name, String sexName, String sexCode, String age, String idNo, String contactPhone, String signState, String archivingDoctorDate, String areaFulladdress, String signOrganizationCode, String areaId, List<com.jqsoft.fingertip_health.bean.propMap> propMap) {
        this.id = id;
        this.personId = personId;
        this.no = no;
        this.jtNo = jtNo;
        this.name = name;
        this.sexName = sexName;
        this.sexCode = sexCode;
        this.age = age;
        this.idNo = idNo;
        this.contactPhone = contactPhone;
        this.signState = signState;
        this.archivingDoctorDate = archivingDoctorDate;
        this.areaFulladdress = areaFulladdress;
        this.signOrganizationCode = signOrganizationCode;
        this.areaId = areaId;
        this.propMap = propMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getJtNo() {
        return jtNo;
    }

    public void setJtNo(String jtNo) {
        this.jtNo = jtNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }

    public String getArchivingDoctorDate() {
        return archivingDoctorDate;
    }

    public void setArchivingDoctorDate(String archivingDoctorDate) {
        this.archivingDoctorDate = archivingDoctorDate;
    }

    public String getAreaFulladdress() {
        return areaFulladdress;
    }

    public void setAreaFulladdress(String areaFulladdress) {
        this.areaFulladdress = areaFulladdress;
    }

    public String getSignOrganizationCode() {
        return signOrganizationCode;
    }

    public void setSignOrganizationCode(String signOrganizationCode) {
        this.signOrganizationCode = signOrganizationCode;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public List<com.jqsoft.fingertip_health.bean.propMap> getPropMap() {
        return propMap;
    }

    public void setPropMap(List<com.jqsoft.fingertip_health.bean.propMap> propMap) {
        this.propMap = propMap;
    }
}
