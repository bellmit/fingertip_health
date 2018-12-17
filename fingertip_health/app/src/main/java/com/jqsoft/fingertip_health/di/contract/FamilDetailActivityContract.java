package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.FamilyDetailbeans;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface FamilDetailActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<FamilyDetailbeans>> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<FamilyDetailbeans>> bean);
        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
