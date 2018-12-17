package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.ReceptionDetailNewListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface ReceptionDetailNewListActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<ReceptionDetailNewListBean> bean);
        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
