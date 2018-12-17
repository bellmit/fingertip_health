package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/1/1.
 */

public class DetailPoorBatch {
    private String poorScore;

    public DetailPoorBatch(String poorScore) {
        this.poorScore = poorScore;
    }

    public DetailPoorBatch() {
    }

    public String getPoorScore() {
        return poorScore;
    }

    public void setPoorScore(String poorScore) {
        this.poorScore = poorScore;
    }
}
