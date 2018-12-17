package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.SocialAssistanceObjectBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface DiabetesMellitusListActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onLoadMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onLoadListFailure(String message, boolean isLoadMore);
    }

    interface  presenter{

    }
}
