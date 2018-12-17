package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.SignServiceAssessResultBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignServiceAssessFragmentContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<SignServiceAssessResultBean>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<SignServiceAssessResultBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
