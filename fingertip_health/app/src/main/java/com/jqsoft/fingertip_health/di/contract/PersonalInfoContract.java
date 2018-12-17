package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PersonalInfoBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface PersonalInfoContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<PersonalInfoBean> bean);
        void onPostSuccess(GCAHttpResultBaseBean<PersonalInfoBean> bean);
        void onPostFailure(String message);
        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
