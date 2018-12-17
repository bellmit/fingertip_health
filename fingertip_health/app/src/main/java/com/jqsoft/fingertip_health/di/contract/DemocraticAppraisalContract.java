package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.DemocraticAppraisalBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public interface DemocraticAppraisalContract {
    interface View {

        void onLoadListSuccess(GCAHttpResultBaseBean<List<DemocraticAppraisalBean>> bean);

        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<DemocraticAppraisalBean>> bean);

        void onLoadListFailure(String message);


    }

    interface presenter {

    }
}
