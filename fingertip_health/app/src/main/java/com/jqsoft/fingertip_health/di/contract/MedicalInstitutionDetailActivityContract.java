package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.MedicalInstitutionDetailBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface MedicalInstitutionDetailActivityContract {
    interface View{

        void onLoadInfoSuccess(HttpResultBaseBean<MedicalInstitutionDetailBean> bean);

        void onLoadInfoFailure(String message);
    }

    interface  presenter{

    }
}
