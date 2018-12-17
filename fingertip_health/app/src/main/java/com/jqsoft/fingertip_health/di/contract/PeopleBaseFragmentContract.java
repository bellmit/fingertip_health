package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface PeopleBaseFragmentContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<PeopleBaseInfoBean> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<PeopleBaseInfoBean> bean);

        void onLoadListFailure(String message, boolean isLoadMore);


        void onLoadDoctorListSuccess(HttpResultBaseBean<List<DoctorTeamInfo>> bean);

        void onLoadDoctorListFailure(String message);

    }

    interface  presenter{

    }
}
