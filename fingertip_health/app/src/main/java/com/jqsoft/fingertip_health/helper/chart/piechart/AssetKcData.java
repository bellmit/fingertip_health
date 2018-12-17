package com.jqsoft.fingertip_health.helper.chart.piechart;

/**
 * Created by Administrator on 2018-05-17.
 */

public class AssetKcData {
    private int color;
    private String desc;
    private float num;
    private String percent;

    public AssetKcData(int color, String desc, float num, String percent) {
        this.color = color;
        this.desc = desc;
        this.num = num;
        this.percent = percent;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getSNum() {
        String result = DecimalFUtil.formatToPercent(percent);
        return result;
//        return DecimalFUtil.formatToNormal(num+"");
    }
}
