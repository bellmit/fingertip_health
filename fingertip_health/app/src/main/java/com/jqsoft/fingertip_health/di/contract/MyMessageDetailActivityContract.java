package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.MyMessageDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface MyMessageDetailActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<MyMessageDetailBean> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<MyMessageDetailBean> bean);
        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
