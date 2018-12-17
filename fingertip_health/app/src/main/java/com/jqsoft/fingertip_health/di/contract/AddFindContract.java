package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.DetailFindBeans;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface AddFindContract {
    interface View{

        void onAddFindSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);
        void onAddFindFailure(String message);

        void onDetailFindSuccess(HttpResultBaseBean<DetailFindBeans> bean);
        void onDetailFindFailure(String message);


    }

    interface  presenter{

    }
}
