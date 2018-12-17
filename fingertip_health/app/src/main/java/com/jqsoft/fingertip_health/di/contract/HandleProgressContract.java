package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.ProgressBean;
import com.jqsoft.fingertip_health.bean.SocailHistoryDetailsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface HandleProgressContract {
    interface View {

        void onLoadListSuccess(GCAHttpResultBaseBean<List<ProgressBean>> bean);

        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<ProgressBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

        void onLoadHistoryDataSuccess(GCAHttpResultBaseBean<List<SocailHistoryDetailsBean>> bean);

        void onLoadHisdataFailure(String message, boolean isLoadMore);


    }

    interface presenter {

    }
}
