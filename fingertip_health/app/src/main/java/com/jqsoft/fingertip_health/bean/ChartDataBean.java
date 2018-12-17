package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2017-05-12.
 */

public class ChartDataBean {
    private BarDataBean barDataBean;
    private PieDataBean pieDataBean;

    public BarDataBean getBarDataBean() {
        return barDataBean;
    }

    public void setBarDataBean(BarDataBean barDataBean) {
        this.barDataBean = barDataBean;
    }

    public PieDataBean getPieDataBean() {
        return pieDataBean;
    }

    public void setPieDataBean(PieDataBean pieDataBean) {
        this.pieDataBean = pieDataBean;
    }
}
