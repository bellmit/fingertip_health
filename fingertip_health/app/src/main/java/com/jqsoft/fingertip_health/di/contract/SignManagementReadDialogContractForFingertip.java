package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignManagementReadDialogContractForFingertip {
    interface View{

        void onLoadListInfoSuccess(HttpResultBaseBeanForFingertip<String> bean);

        void onLoadListInfoFailure(String message);


    }

    interface  presenter{

    }
}
