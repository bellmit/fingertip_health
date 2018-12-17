package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface AddFamilyMemberActivityContract {
    interface View{


        void onAddFamilyMemberSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);

        void onAddFamilyMemberFailure(String msg);
    }

    interface  presenter{

    }
}
