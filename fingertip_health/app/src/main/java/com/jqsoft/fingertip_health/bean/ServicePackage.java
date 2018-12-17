package com.jqsoft.fingertip_health.bean;

import java.io.Serializable;

/**
 * Created by Mars on 2018/9/14.
 */

public class ServicePackage implements Serializable{
    private String id;
    private  String packageName;
    private String serviceContent;

    public ServicePackage() {
    }

    public ServicePackage(String id, String packageName, String serviceContent) {
        this.id = id;
        this.packageName = packageName;
        this.serviceContent = serviceContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }
}
