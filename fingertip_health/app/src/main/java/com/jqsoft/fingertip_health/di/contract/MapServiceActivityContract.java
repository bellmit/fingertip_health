package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HeatmapBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultMapBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonStreetOrAboveLocationBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface MapServiceActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultMapBaseBean<List<PersonStreetOrAboveLocationBean>> bean);
        void onLoadMoreListSuccess(GCAHttpResultMapBaseBean<List<PersonStreetOrAboveLocationBean>> bean);

//        void onLoadListSuccess(GCAHttpResultBaseBean<List<PersonLocationBean>> bean);
//        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<PersonLocationBean>> bean);
//
        void onLoadListFailure(String message, boolean isLoadMore);

        void onLoadHeatmapSuccess(GCAHttpResultBaseBean<HeatmapBean> bean);
        void onLoadHeatmapFailure(String msg);
    }

    interface  presenter{

    }
}
