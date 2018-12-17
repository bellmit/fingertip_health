package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.TownLevelMedicalInstitutionBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface TownLevelMedicalInstitutionDirectoryFragmentContract {
    interface View{

        void onLoadListSuccess(HttpResultBaseBean<List<TownLevelMedicalInstitutionBeanList>> bean);
        void onLoadMoreListSuccess(HttpResultBaseBean<List<TownLevelMedicalInstitutionBeanList>> bean);

        void onLoadListFailure(String message, boolean isLoadMore);

    }

    interface  presenter{

    }
}
