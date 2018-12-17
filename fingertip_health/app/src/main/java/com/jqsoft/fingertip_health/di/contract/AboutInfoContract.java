package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.AboutInfoBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;



public interface AboutInfoContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<AboutInfoBean> bean);


        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
