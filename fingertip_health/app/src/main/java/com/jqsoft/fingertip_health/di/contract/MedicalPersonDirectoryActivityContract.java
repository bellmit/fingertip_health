package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response.MedicalPersonDirectoryResultBean;

import java.util.List;


public interface MedicalPersonDirectoryActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<MedicalPersonDirectoryResultBean>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<MedicalPersonDirectoryResultBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
