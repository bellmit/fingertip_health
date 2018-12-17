package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.MedicalInstitutionListBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface MedicalInstitutionActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<MedicalInstitutionListBean> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<MedicalInstitutionListBean> bean);

        void onLoadListFailure(String message);
    }

    interface  presenter{

    }
}
