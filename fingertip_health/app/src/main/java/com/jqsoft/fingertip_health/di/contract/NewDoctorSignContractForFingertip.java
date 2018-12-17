package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface NewDoctorSignContractForFingertip {
    interface View{

        void onSubmitInfoSuccess(HttpResultBaseBeanForFingertip<String> bean);

        void onSubmitInfoFailure(String message);


    }

    interface  presenter{

    }
}
