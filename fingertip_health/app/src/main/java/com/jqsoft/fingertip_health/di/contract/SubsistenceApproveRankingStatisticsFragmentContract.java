package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApproveRankingStatisticsBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SubsistenceApproveRankingStatisticsFragmentContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<SubsistenceApproveRankingStatisticsBean>> bean);

        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
