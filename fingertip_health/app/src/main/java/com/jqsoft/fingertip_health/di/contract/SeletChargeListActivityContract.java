package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;


public interface SeletChargeListActivityContract {
    interface View{

        void onSeletChargeListSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onSeletChargeMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onSeletChargeListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
