package com.jqsoft.fingertip_health.bean.base;

/**
 * Created by Administrator on 2017-06-02.
 */

public class HttpListRequestBaseBean extends HttpRequestBaseBean {
    private String keyword="";//关键字
    private String area="";//地区
    private int page;//页数
    private int size;//行数

    public HttpListRequestBaseBean() {
    }

    public HttpListRequestBaseBean(String keyword, String area, int page, int size) {
        this.keyword = keyword;
        this.area = area;
        this.page = page;
        this.size = size;
    }

    public HttpListRequestBaseBean(String v, String timestamp, String token, String appkey, String sig, String keyword, String area, int page, int size) {
        super(v, timestamp, token, appkey, sig);
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
