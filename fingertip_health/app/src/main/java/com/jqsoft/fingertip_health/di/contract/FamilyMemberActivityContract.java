package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.FamilyMemberBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface FamilyMemberActivityContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<FamilyMemberBean>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<FamilyMemberBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);


    }

    interface  presenter{

    }
}
