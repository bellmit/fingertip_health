package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.response_new.IndexAndOnlineSignInitialData;

import java.util.List;


public interface SignApplicationActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<IndexAndOnlineSignInitialData>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

        void onCancelSignApplicationSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);
        void onCancelSignApplicationFailure(String message);

    }

    interface  presenter{

    }
}
