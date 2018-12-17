package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

/**
 * 低保档案排名统计可读bean
 * Created by Administrator on 2018-01-04.
 */

public class SubsistenceAccountRankingNaturalBean {
    private String districtName;
    private String money;
    public SubsistenceAccountRankingNaturalBean() {
        super();
    }

    public SubsistenceAccountRankingNaturalBean(String districtName, String money) {
        this.districtName = districtName;
        this.money = money;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
