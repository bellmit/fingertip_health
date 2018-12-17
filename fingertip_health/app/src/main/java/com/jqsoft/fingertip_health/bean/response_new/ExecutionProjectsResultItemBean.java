package com.jqsoft.fingertip_health.bean.response_new;

import com.jqsoft.fingertip_health.bean.PendExecuBeanList;

/**
 * Created by Administrator on 2017-07-04.
 */

//最近7天执行项目或超时未执行项目里的一项
public class ExecutionProjectsResultItemBean {
    private String name;
    private String nxetdate;
    private String serviceContentID;
    private String serviceContentItemsID;
    private String signDetailID;

    private ExecutionProjectsResultBean parentBean;
   // private PendExecuBeanList signPromExec = new PendExecuBeanList();
    private PendExecuBeanList signPromExec = new PendExecuBeanList();
   public ExecutionProjectsResultItemBean() {
       super();
   }

    public ExecutionProjectsResultItemBean(String name, String nxetdate, String serviceContentID, String serviceContentItemsID, String signDetailID) {
        this.name = name;
        this.nxetdate = nxetdate;
        this.serviceContentID = serviceContentID;
        this.serviceContentItemsID = serviceContentItemsID;
        this.signDetailID = signDetailID;
        //this.signPromExec = signPromExec;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNxetdate() {
        return nxetdate;
    }

    public void setNxetdate(String nxetdate) {
        this.nxetdate = nxetdate;
    }

    public String getServiceContentID() {
        return serviceContentID;
    }

    public void setServiceContentID(String serviceContentID) {
        this.serviceContentID = serviceContentID;
    }

    public String getServiceContentItemsID() {
        return serviceContentItemsID;
    }

    public void setServiceContentItemsID(String serviceContentItemsID) {
        this.serviceContentItemsID = serviceContentItemsID;
    }

    public String getSignDetailID() {
        return signDetailID;
    }

    public void setSignDetailID(String signDetailID) {
        this.signDetailID = signDetailID;
    }

    public ExecutionProjectsResultBean getParentBean() {
        return parentBean;
    }

    public void setParentBean(ExecutionProjectsResultBean parentBean) {
        this.parentBean = parentBean;
    }

//    public PendExecuBeanList getSignPromExec() {
//        return signPromExec;
//    }
//
//    public void setSignPromExec(PendExecuBeanList signPromExec) {
//        this.signPromExec = signPromExec;
//    }
    public PendExecuBeanList getSignPromExec() {
        return signPromExec;
    }

    public void setSignPromExec(PendExecuBeanList signPromExec) {
        this.signPromExec = signPromExec;
    }


}
