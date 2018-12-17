package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.SignTeamBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignTeamContract {
    interface View{

        void onLoginSuccess(HttpResultBaseBean<SignTeamBean> bean);

        void onLoginFailure(String message);
    }

    interface  presenter{

    }
}
