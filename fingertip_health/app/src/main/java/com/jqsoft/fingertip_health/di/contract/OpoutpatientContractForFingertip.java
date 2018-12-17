package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface OpoutpatientContractForFingertip {
    interface View {

        void onOpoutpatientSccusse(HttpResultBaseBeanForFingertip<String> bean);

        void onLoginFailure(String message);

        void onPrescribel(HttpResultBaseBeanForFingertip<String> bean);

        void onPrescribelFailure(String message);

        void onbudgeting(HttpResultBaseBeanForFingertip<String> bean);

        void ononbudgetinglFailure(String message);
    }
    interface presenter {

    }
}
