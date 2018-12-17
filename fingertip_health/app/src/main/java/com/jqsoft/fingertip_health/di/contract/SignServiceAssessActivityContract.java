package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.MyFindResultBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignServiceAssessActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<MyFindResultBean> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<MyFindResultBean> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

        void onReadSignServiceAssessItemSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);
        void onReadSignServiceAssessItemFailure(String message);

    }

    interface  presenter{

    }
}
