package com.jqsoft.fingertip_health.bean.response_new;

/**
 * Created by Jerry on 2017/7/21.
 */

public class ServicePackOnlineSignBean{
    private String nhcompensateProjName;//（1:基础包 2:初级包 3: 中级包 4:高级包 5 其他）
    private String fwmc;//服务包名称
    private String fwnr;//服务内容
    private String sydx;//适用对象
    private String hjnysfje;//总收入（总金额）
    private String xnhbcje;//新农合应予补偿金额
    private String qtjmje;//其他减免金额
    private String sjzfje;//实际自付金额
    private String cdje;//基本公共卫生服务经费承担金额
    private String serviceCententFee;//实收金额
    private String key;//服务包主键
    private String isPersonality;//是否个性化服务
    private String year;//服务包年份

    public ServicePackOnlineSignBean() {
        super();
    }

    public ServicePackOnlineSignBean(String nhcompensateProjName, String fwmc, String fwnr, String sydx, String hjnysfje, String xnhbcje, String qtjmje, String sjzfje, String cdje, String serviceCententFee, String key, String isPersonality, String year) {
        this.nhcompensateProjName = nhcompensateProjName;
        this.fwmc = fwmc;
        this.fwnr = fwnr;
        this.sydx = sydx;
        this.hjnysfje = hjnysfje;
        this.xnhbcje = xnhbcje;
        this.qtjmje = qtjmje;
        this.sjzfje = sjzfje;
        this.cdje = cdje;
        this.serviceCententFee = serviceCententFee;
        this.key = key;
        this.isPersonality = isPersonality;
        this.year = year;
    }

    public String getNhcompensateProjName() {
        return nhcompensateProjName;
    }

    public void setNhcompensateProjName(String nhcompensateProjName) {
        this.nhcompensateProjName = nhcompensateProjName;
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

    public String getSydx() {
        return sydx;
    }

    public void setSydx(String sydx) {
        this.sydx = sydx;
    }

    public String getHjnysfje() {
        return hjnysfje;
    }

    public void setHjnysfje(String hjnysfje) {
        this.hjnysfje = hjnysfje;
    }

    public String getXnhbcje() {
        return xnhbcje;
    }

    public void setXnhbcje(String xnhbcje) {
        this.xnhbcje = xnhbcje;
    }

    public String getQtjmje() {
        return qtjmje;
    }

    public void setQtjmje(String qtjmje) {
        this.qtjmje = qtjmje;
    }

    public String getSjzfje() {
        return sjzfje;
    }

    public void setSjzfje(String sjzfje) {
        this.sjzfje = sjzfje;
    }

    public String getCdje() {
        return cdje;
    }

    public void setCdje(String cdje) {
        this.cdje = cdje;
    }

    public String getServiceCententFee() {
        return serviceCententFee;
    }

    public void setServiceCententFee(String serviceCententFee) {
        this.serviceCententFee = serviceCententFee;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}
