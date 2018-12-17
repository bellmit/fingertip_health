package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SignSeverPakesContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<SignSeverPakesBeanList>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<SignSeverPakesBeanList>> bean);

        void onLoadListFailure(String message);
    }

    interface  presenter{

    }
}
