package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NotificationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolicyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator 2017/5/21.
 */

public interface IndexFragmentContract {
    interface View{

        void onLoadNotificationSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onLoadNotificationFailure(String msg);

        void onLoadOutpatientStatisticsSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onLoadOutpatientStatisticsFailure(String msg);

        void onLoadNotificationDataSuccess(GCAHttpResultBaseBean<List<NotificationBean>> bean);

        void onLoadNotificationDataFailure(String message);

        void onLoadPolicyDataSuccess(GCAHttpResultBaseBean<List<PolicyBean>> bean);

        void onLoadPolicyDataFailure(String message);

    }

    interface  presenter{

    }
}
