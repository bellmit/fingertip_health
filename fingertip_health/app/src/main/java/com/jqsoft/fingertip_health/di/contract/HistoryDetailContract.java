package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.SocailHistoryDetailsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface HistoryDetailContract {
    interface View {

        void onLoadHistoryDataSuccess(GCAHttpResultBaseBean<List<SocailHistoryDetailsBean>> bean);

        void onLoadHisdataFailure(String message, boolean isLoadMore);


    }

    interface presenter {

    }
}
