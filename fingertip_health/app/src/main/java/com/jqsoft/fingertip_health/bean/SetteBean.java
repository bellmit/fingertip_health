package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/9/13.
 */

public class SetteBean {
    private String invoiceNumber;

    public SetteBean(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public SetteBean() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
