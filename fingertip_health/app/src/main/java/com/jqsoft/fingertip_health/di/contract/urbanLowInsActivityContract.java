package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowInsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface urbanLowInsActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<UrbanLowInsBean>> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<UrbanLowInsBean>> bean);
        void onLoadListFailure(String message, boolean isLoadMore);

        void onDeleteUrbanSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean);
        void onDeleteUrbanFailure(String message);


    }

    interface  presenter{

    }
}
