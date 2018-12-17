package com.jqsoft.fingertip_health.bean;

public class SignSeverPakesBeanList implements Cloneable {
    private String fwmc;
    private String fwnr;
    private String nhcompensateProjName;
    private String hjnysfje;
    private String key;
    private String qtjmje;
    private String serviceCententFee;
    private String sjzfje;
    private String sydx;
    private String xnhbcje;
    private String isPersonality;//是否个性化服务
    private String year;//服务包年份
    private String cdje;


    public SignSeverPakesBeanList(String fwmc, String fwnr, String nhcompensateProjName, String hjnysfje, String key, String qtjmje, String serviceCententFee, String sjzfje, String sydx, String xnhbcje, String isPersonality, String year, String cdje) {
        this.fwmc = fwmc;
        this.fwnr = fwnr;
        this.nhcompensateProjName = nhcompensateProjName;
        this.hjnysfje = hjnysfje;
        this.key = key;
        this.qtjmje = qtjmje;
        this.serviceCententFee = serviceCententFee;
        this.sjzfje = sjzfje;
        this.sydx = sydx;
        this.xnhbcje = xnhbcje;
        this.isPersonality = isPersonality;
        this.year = year;
        this.cdje = cdje;

    }

    public SignSeverPakesBeanList() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SignSeverPakesBeanList bean = new SignSeverPakesBeanList();
        bean.setFwmc(getFwmc());
        bean.setFwnr(getFwnr());
        bean.setNhcompensateProjName(getNhcompensateProjName());
        bean.setHjnysfje(getHjnysfje());
        bean.setKey(getKey());
        bean.setQtjmje(getQtjmje());
        bean.setServiceCententFee(getServiceCententFee());
        bean.setSjzfje(getSjzfje());
        bean.setSydx(getSydx());
        bean.setXnhbcje(getXnhbcje());
        bean.setIsPersonality(getIsPersonality());
        bean.setYear(getYear());
        bean.setCdje(getCdje());

        return bean;
    }

    public String getFwmc() {
        return fwmc;
    }

    public void setFwmc(String fwmc) {
        this.fwmc = fwmc;
    }

    public String getFwnr() {
        return fwnr;
    }

    public void setFwnr(String fwnr) {
        this.fwnr = fwnr;
    }

    public String getNhcompensateProjName() {
        return nhcompensateProjName;
    }

    public void setNhcompensateProjName(String nhcompensateProjName) {
        this.nhcompensateProjName = nhcompensateProjName;
    }

    public String getHjnysfje() {
        return hjnysfje;
    }

    public void setHjnysfje(String hjnysfje) {
        this.hjnysfje = hjnysfje;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQtjmje() {
        return qtjmje;
    }

    public void setQtjmje(String qtjmje) {
        this.qtjmje = qtjmje;
    }

    public String getServiceCententFee() {
        return serviceCententFee;
    }

    public void setServiceCententFee(String serviceCententFee) {
        this.serviceCententFee = serviceCententFee;
    }

    public String getSjzfje() {
        return sjzfje;
    }

    public void setSjzfje(String sjzfje) {
        this.sjzfje = sjzfje;
    }

    public String getSydx() {
        return sydx;
    }

    public void setSydx(String sydx) {
        this.sydx = sydx;
    }

    public String getXnhbcje() {
        return xnhbcje;
    }

    public void setXnhbcje(String xnhbcje) {
        this.xnhbcje = xnhbcje;
    }

    public String getIsPersonality() {
        return isPersonality;
    }

    public void setIsPersonality(String isPersonality) {
        this.isPersonality = isPersonality;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCdje() {
        return cdje;
    }

    public void setCdje(String cdje) {
        this.cdje = cdje;
    }
}
