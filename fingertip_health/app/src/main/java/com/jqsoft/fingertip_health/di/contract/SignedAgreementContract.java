package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.PersonSignAgreement;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignedAgreementContract {
    interface View {
        void onLoginSuccess(HttpResultBaseBean<PersonSignAgreement> bean);

        void onLoginFailure(String message);
    }

    interface presenter {

    }
}
