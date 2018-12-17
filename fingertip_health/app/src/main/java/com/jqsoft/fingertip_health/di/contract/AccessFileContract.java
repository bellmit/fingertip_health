package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.PersonnelInfoData;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface AccessFileContract {
    interface View{

        void onLoadAccessFileSuccess(HttpResultBaseBean<PersonnelInfoData> bean);
        void onLoadAccessFileFailure(String message);

        void onLoadUpdatePeopleSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);
        void onLoadUpdatePeopleFailure(String message);

    }

    interface  presenter{

    }
}
