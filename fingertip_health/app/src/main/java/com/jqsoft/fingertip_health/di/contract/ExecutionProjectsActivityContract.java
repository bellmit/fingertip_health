package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.RemindBean;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface ExecutionProjectsActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<ExecutionProjectsResultBean>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<ExecutionProjectsResultBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);
        void onLoadRemindDataSuccess(HttpResultBaseBean<List<RemindBean>> bean);

        void onLoadRemindDataFailure(String message);
    }

    interface  presenter{

    }
}
