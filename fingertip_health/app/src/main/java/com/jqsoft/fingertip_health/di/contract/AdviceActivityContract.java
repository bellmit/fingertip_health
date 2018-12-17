package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.AdviceBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface AdviceActivityContract {
    interface View{

        void onReplyLoadListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> bean);
        void onNoReplyLoadListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> bean);
        void onLoadreplyMoreListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> bean);
        void  onnoreplyLoadMoreListSuccess(GCAHttpResultBaseBean<List<AdviceBean>> bean);
        void onLoadListFailure(String message, boolean isLoadMore,String fromwhere);


    }

    interface  presenter{

    }
}
