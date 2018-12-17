package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolityBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface PolityActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<PolityBean>> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<PolityBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);


    }

    interface  presenter{

    }
}
