package com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base;

/**
 * 地图点信息bean
 * type= total时表示返回的街道及以上区划的分类汇总信息
 type=point时表示返回的是街道、社区的分类详细信息
 * Created by Administrator on 2018-04-25.
 */

public class GCAHttpResultMapBaseBean<T> extends GCAHttpResultBaseBean<T> {
    private String type;

    public GCAHttpResultMapBaseBean() {
        super();
    }

    public GCAHttpResultMapBaseBean(String result, String msg, T data) {
        super(result, msg, data);
    }

    public GCAHttpResultMapBaseBean(String result, String msg, T data, String type) {
        super(result, msg, data);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
