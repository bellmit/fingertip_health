package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.SaveWanttoAdvicebean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

/**
 * Created by Administrator on 2018/1/22.
 */

public interface WanttoAdviceContract {
    interface View {

        void onLoadListSuccess(HttpResultBaseBean<SaveWanttoAdvicebean> bean);

        void onLoadListFailure(String message);


    }

    interface presenter {

    }
}
