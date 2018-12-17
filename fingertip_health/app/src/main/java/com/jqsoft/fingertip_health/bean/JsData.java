package com.jqsoft.fingertip_health.bean;

public class JsData {
    public String[] categories;
    public YData[] series;

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public YData[] getSeries() {
        return series;
    }

    public void setSeries(YData[] series) {
        this.series = series;
    }
}