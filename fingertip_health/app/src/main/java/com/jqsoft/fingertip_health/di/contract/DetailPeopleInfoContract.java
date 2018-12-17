package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;


public interface DetailPeopleInfoContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onLoadListFailure(String message);

    }

    interface  presenter{

    }
}
