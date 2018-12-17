package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReliefItemBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface ReliefItemActivityContract {
    interface View{
        void onLoadListSuccessfromCollection(GCAHttpResultBaseBean<List<ReliefItemBean>> bean);

        void onLoadListSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean);
        void oncollectionSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean);
        void onremovecollectionSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean);
         void      onremovecollectionFailure(String message);
        void oncollectionFailure(String message);
        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
