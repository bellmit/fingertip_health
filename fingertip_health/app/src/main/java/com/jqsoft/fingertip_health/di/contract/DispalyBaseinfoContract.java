package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.HouseHoldeBackBean;
import com.jqsoft.fingertip_health.bean.HttpResultTestBean;
import com.jqsoft.fingertip_health.bean.Uploadpic;
import com.jqsoft.fingertip_health.bean.VideoBackBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface DispalyBaseinfoContract {
    interface View{

        void onAddFindSuccess(HttpResultTestBean bean);
        void onAddFindFailure(String message);

        void onLoadListSuccess(GCAHttpResultBaseBean<VideoBackBean> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<VideoBackBean> bean);
        void onLoadListFailure(String message, boolean isLoadMore);
        void onAddpicSuccess(GCAHttpResultBaseBean<List<Uploadpic>> bean);
        void onLoadMorepicListSuccess(GCAHttpResultBaseBean<List<Uploadpic>> bean);
        void onAddpicFailure(String message);


        void onLoadListSuccessx(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean);
        void onLoadMoreListSuccessx(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean);
        void onLoadListFailurex(String message, boolean isLoadMore);


    }

    interface  presenter{

    }
}
