package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.util.List;

/**
 * Created by Administrator on 2018-02-02.
 */

public class HeatmapBean {
    private String max;
    private List<LngLatCount> points;
    public HeatmapBean() {
        super();
    }

    public HeatmapBean(String max, List<LngLatCount> points) {
        this.max = max;
        this.points = points;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public List<LngLatCount> getPoints() {
        return points;
    }

    public void setPoints(List<LngLatCount> points) {
        this.points = points;
    }
}
