package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface HandleFindContract {
    interface View{

        void onHandleFindSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);
        void onHandleFindFailure(String message);



    }

    interface  presenter{

    }
}
