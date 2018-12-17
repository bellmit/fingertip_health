package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApprovePovertyReasonBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SubsistenceApprovePovertyReasonStatisticsFragmentContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<SubsistenceApprovePovertyReasonBean>> bean);

        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
