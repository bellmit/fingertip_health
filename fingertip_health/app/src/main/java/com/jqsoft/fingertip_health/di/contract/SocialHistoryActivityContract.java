package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.SocialListHistoryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface SocialHistoryActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<SocialListHistoryBean>> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<SocialListHistoryBean>> bean);
        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
