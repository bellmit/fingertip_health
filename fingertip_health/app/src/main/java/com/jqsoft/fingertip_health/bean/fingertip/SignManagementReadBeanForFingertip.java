package com.jqsoft.fingertip_health.bean.fingertip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018-09-14.
 */

public class SignManagementReadBeanForFingertip {
    private String id;//	主键
    private String sexCode;//	性别类型
    private String sexName;//	性别名称
    private String name;//	姓名
    private String birthday;//	出生日期
    private String areaFulladdress;//	详细地址
    private String nationCode;//	民族编码
    private String nationName;//	民族名称
    private String areaHouseno;//	居住门牌号
    private String idNo;//	身份证号
    private String phone;//	手机号码
    private String residentType;//	常住类型
    private String hujiType;//	户籍类型
    private String areavillageCode;//	居住村编码
    private String age;//	年龄
    private String properties;
    private List<Map> propMap =new ArrayList<>();

    public SignManagementReadBeanForFingertip() {
        super();
    }

    public SignManagementReadBeanForFingertip(List<Map>propMap ,String id, String sexCode, String sexName, String name, String birthday, String areaFulladdress, String nationCode, String nationName, String areaHouseno, String idNo, String phone, String residentType, String hujiType, String areavillageCode, String age,String properties) {
        this.propMap= propMap;
        this.id = id;
        this.sexCode = sexCode;
        this.sexName = sexName;
        this.name = name;
        this.birthday = birthday;
        this.areaFulladdress = areaFulladdress;
        this.nationCode = nationCode;
        this.nationName = nationName;
        this.areaHouseno = areaHouseno;
        this.idNo = idNo;
        this.phone = phone;
        this.residentType = residentType;
        this.hujiType = hujiType;
        this.areavillageCode = areavillageCode;
        this.age = age;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAreaFulladdress() {
        return areaFulladdress;
    }

    public void setAreaFulladdress(String areaFulladdress) {
        this.areaFulladdress = areaFulladdress;
    }

    public List<Map> getPropMap() {
        return propMap;
    }

    public void setPropMap(List<Map> propMap) {
        this.propMap = propMap;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getAreaHouseno() {
        return areaHouseno;
    }

    public void setAreaHouseno(String areaHouseno) {
        this.areaHouseno = areaHouseno;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }

    public String getHujiType() {
        return hujiType;
    }

    public void setHujiType(String hujiType) {
        this.hujiType = hujiType;
    }

    public String getAreavillageCode() {
        return areavillageCode;
    }

    public void setAreavillageCode(String areavillageCode) {
        this.areavillageCode = areavillageCode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
