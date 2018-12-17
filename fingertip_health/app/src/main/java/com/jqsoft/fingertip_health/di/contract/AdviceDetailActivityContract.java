package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.AdviceDetailBean;
import com.jqsoft.fingertip_health.bean.SaveWanttoAdvicebean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface AdviceDetailActivityContract {
    interface View{
        void onconsultAgainSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean);
        void onreplyConsultSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean);
        void onLoadListSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean);
        void onreplyConsultFailure(String message);
        void onconsultAgainFailure(String message);
        void onLoadListFailure(String message);
        void onAdviceremoveSuccess(GCAHttpResultBaseBean<SaveWanttoAdvicebean> bean);
        void onturnConsultSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean);



    }

    interface  presenter{

    }
}
