package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.ProgressDetailbean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;


public interface HandleProgressDetailActivityContract {
    interface View {

        void onLoadListSuccess(GCAHttpResultBaseBean<ProgressDetailbean> bean);

        void onLoadMoreListSuccess(GCAHttpResultBaseBean<ProgressDetailbean> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface presenter {

    }
}
