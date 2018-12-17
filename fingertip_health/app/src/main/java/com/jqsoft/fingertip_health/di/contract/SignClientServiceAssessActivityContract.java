package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.response_new.SignClientServiceAssessResultBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignClientServiceAssessActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<SignClientServiceAssessResultBean>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<SignClientServiceAssessResultBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

        void onReadSignServiceAssessItemSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);
        void onReadSignServiceAssessItemFailure(String message);

    }

    interface  presenter{

    }
}
