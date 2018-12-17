package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;

/**
 * Created by Jerry on 2017/6/27.
 */

public class ServicePackDetailBean {
    public ArrayList<ServicePackDetailBeanList> beanLists ;



    public ServicePackDetailBean() {
    }

    public ArrayList<ServicePackDetailBeanList> getBeanLists() {
        return beanLists;
    }

    public void setBeanLists(ArrayList<ServicePackDetailBeanList> beanLists) {
        this.beanLists = beanLists;
    }



}
