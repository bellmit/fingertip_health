package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.HouseHoldeBackBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface AddServeryActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<HouseHoldeBackBean>> bean);
        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
