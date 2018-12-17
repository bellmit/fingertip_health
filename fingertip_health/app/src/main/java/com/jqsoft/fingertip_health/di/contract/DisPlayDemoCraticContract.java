package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.DemoCraticBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;


public interface DisPlayDemoCraticContract {
    interface View {

        void onLoadListSuccess(GCAHttpResultBaseBean<DemoCraticBaseBean> bean);

        void onLoadMoreListSuccess(GCAHttpResultBaseBean<DemoCraticBaseBean> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface presenter {

    }
}
