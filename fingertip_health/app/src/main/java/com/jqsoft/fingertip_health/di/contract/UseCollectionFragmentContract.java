package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PersonCollectionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;


public interface UseCollectionFragmentContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<PersonCollectionBean>> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<PersonCollectionBean>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);


    }

    interface  presenter{

    }
}
