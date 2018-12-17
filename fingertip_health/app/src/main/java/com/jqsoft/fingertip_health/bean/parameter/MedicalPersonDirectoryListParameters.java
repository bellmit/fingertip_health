package com.jqsoft.fingertip_health.bean.parameter;

import com.jqsoft.fingertip_health.annotation.HttpParameter;
import com.jqsoft.fingertip_health.bean.parameter.base.CommonParameters;


public class MedicalPersonDirectoryListParameters extends CommonParameters {
    @HttpParameter
    private String keyword;//关键字
    @HttpParameter
    private String area;//地区
    @HttpParameter
    private int page;//页数
    @HttpParameter
    private int size;//行数

    public MedicalPersonDirectoryListParameters() {
        super();
    }

    public MedicalPersonDirectoryListParameters(String appkey, String timestamp, String token, String sig, String v) {
        super(appkey, timestamp, token, sig, v);
    }

    public MedicalPersonDirectoryListParameters(String appkey, String timestamp, String token, String sig, String v, String keyword, String area, int page, int size) {
        super(appkey, timestamp, token, sig, v);
        this.keyword = keyword;
        this.area = area;
        this.page = page;
        this.size = size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
