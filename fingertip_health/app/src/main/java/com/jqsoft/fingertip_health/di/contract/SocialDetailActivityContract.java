package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.SocialDetailBean;
import com.jqsoft.fingertip_health.bean.SubmitMapLocationResultBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;


public interface SocialDetailActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<SocialDetailBean> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<SocialDetailBean> bean);
        void onLoadListFailure(String message, boolean isLoadMore);

        void onSubmitMapLocationSuccess(SubmitMapLocationResultBean bean);
        void onSubmitMapLocationFailure(String message);
    }

    interface  presenter{

    }
}
