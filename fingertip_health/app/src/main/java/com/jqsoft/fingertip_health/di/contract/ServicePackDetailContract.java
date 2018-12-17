package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.ServicePackDetailBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

import java.util.List;


/**
 * Created by Administrator on 2017/5/21.
 */

public interface ServicePackDetailContract {
    interface View{

        void onServicePackDetailSuccess(HttpResultBaseBean<List<ServicePackDetailBeanList>> bean);

        void onLoadMoreServicePackDetailSuccess(HttpResultBaseBean<List<ServicePackDetailBeanList>> bean);

        void onServicePackDetailFailure(String message);
    }

    interface  presenter{

    }
}
