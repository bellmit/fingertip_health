package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.SocialAssistanceObjectBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface SocialAssistanceObjectActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<SocialAssistanceObjectBean>> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<SocialAssistanceObjectBean>> bean);
        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
