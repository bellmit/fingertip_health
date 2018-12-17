package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignPeopleManagementActivityContractForFingertip {
    interface View{

        void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean);
//        void onLoadMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean);

        void onLoadListFailure(String message, boolean isLoadMore);


    }

    interface  presenter{

    }
}
