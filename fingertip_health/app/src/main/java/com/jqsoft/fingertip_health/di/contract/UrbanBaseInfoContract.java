package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.UrbanbaseInfoSaveBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanbaseInfobianjiBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface UrbanBaseInfoContract {
    interface View{

        void onUrbanBaseInfoSaveSuccess(HttpResultBaseBean<UrbanbaseInfoSaveBean> bean);
        void onUrbanBaseInfoSaveFailure(String message);


        void onUrbanBaseInfobianjiSuccess(HttpResultBaseBean<UrbanbaseInfobianjiBean> bean);
        void onUrbanBaseInfobianjiFailure(String message);
    }

    interface  presenter{

    }
}
