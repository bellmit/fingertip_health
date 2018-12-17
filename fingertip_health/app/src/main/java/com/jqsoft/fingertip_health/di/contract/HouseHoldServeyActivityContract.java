package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.HouseHoldSurveyBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface HouseHoldServeyActivityContract {
    interface View {

        void onLoadListSuccess(HttpResultBaseBean<HouseHoldSurveyBean> bean);

        void onLoadMoreListSuccess(HttpResultBaseBean<HouseHoldSurveyBean> bean);

        void onLoadListFailure(String message, boolean isLoadMore);


    }

    interface presenter {

    }
}
