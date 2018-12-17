package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface LoginContract {
    interface View{

        void onLoginSuccess(HttpResultBaseBean<LoginResultBean2> bean);
        void onLoginFailure(String message);
    }

    interface  presenter{

    }
}
