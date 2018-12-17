package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.ReservationBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;

import java.util.List;


/**
 * Created by Administrator on 2017/5/21.
 */

public interface ReservationContract {
    interface View{

        void onReservationSuccess(HttpResultBaseBean<List<ReservationBeanList>> bean);

        void onLoadMoreReservationSuccess(HttpResultBaseBean<List<ReservationBeanList>> bean);

        void onReservationFailure(String message);

        void onLoadDeleteSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);

        void onDeleteFailure(String message);



    }

    interface  presenter{

    }
}
