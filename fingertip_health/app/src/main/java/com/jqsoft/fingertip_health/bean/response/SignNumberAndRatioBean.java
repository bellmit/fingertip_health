package com.jqsoft.fingertip_health.bean.response;

/**
 * Created by Administrator on 2017-06-21.
 */

public class SignNumberAndRatioBean {
    private String signNumberHint;
    private String signNumber;
    private String signRatioHint;
    private String signRatio;

    public SignNumberAndRatioBean() {
    }

    public SignNumberAndRatioBean(String signNumberHint, String signNumber, String signRatioHint, String signRatio) {
        this.signNumberHint = signNumberHint;
        this.signNumber = signNumber;
        this.signRatioHint = signRatioHint;
        this.signRatio = signRatio;
    }

    public String getSignNumberHint() {
        return signNumberHint;
    }

    public void setSignNumberHint(String signNumberHint) {
        this.signNumberHint = signNumberHint;
    }

    public String getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(String signNumber) {
        this.signNumber = signNumber;
    }

    public String getSignRatioHint() {
        return signRatioHint;
    }

    public void setSignRatioHint(String signRatioHint) {
        this.signRatioHint = signRatioHint;
    }

    public String getSignRatio() {
        return signRatio;
    }

    public void setSignRatio(String signRatio) {
        this.signRatio = signRatio;
    }
}
