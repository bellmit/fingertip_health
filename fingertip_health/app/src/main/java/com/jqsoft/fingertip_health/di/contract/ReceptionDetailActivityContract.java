package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface ReceptionDetailActivityContract {
    interface View{
        void onLoadListSuccessfromCollection(GCAHttpResultBaseBean<List<ReceptionDetailBean>> bean);
        void onLoadListSuccess(GCAHttpResultBaseBean<ReceptionDetailBean> bean);
        void onCollectionSuccess(GCAHttpResultBaseBean<ReceptionDetailBean> bean);
        void onremoveCollectionSuccess(GCAHttpResultBaseBean<ReceptionDetailBean> bean);
        void onremoveCollectionFailure(String message);
        void onLoadListFailure(String message);
        void onCollectionFailure(String message);


    }

    interface  presenter{

    }
}
