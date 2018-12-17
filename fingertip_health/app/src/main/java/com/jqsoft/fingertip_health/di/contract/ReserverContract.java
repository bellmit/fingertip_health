package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;


/**
 * Created by Administrator on 2017/5/21.
 */

public interface ReserverContract {
    interface View{

        void onSaveReserverSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);

        void onLoadSaveReserverFailure(String message);

    }

    interface  presenter{

    }
}
