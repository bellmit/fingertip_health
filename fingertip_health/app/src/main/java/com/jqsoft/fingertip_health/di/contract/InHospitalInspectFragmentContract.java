package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface InHospitalInspectFragmentContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<InHospitalInspectBeanList>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<InHospitalInspectBeanList>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);
    }

    interface  presenter{

    }
}
