package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface FormalsettlementContractForFingertip {
    interface View {

        void onFormalsettlementSccusse(HttpResultBaseBeanForFingertip<String> bean);

        void onFormalsettlementFailure(String message);


    }
    interface presenter {

    }
}
