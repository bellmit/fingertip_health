package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NotificationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.PolicyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator 2017/5/21.
 */

public interface TreatFragmentContract {
    interface View {
        void onTreatSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onTreatFailure(String msg);
    }

    interface presenter {

    }
}
