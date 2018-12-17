package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.OrganizationBean;
import com.jqsoft.fingertip_health.bean.PcodeDataBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginSalvationBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface SRCLoginContract {
    interface View{

        void onLoginSuccess(HttpResultBaseBeanForFingertip<String> bean);

        void onLoginFailure(String message);


//        void onLoginSuccess(HttpResultBaseBean<SRCLoginBean> bean);
//
//        void onLoginFailure(String message);

        void onLoginAreaSuccess(HttpResultBaseBean<List<SRCLoginAreaBean>> bean);
        void onLoginAreaFailure(String message);

        void onLoginDataDictionatySuccess(HttpResultBaseBean<List<SRCLoginDataDictionaryBean>> bean);
        void onLoginDataDictionatyFailure(String message);

        void onLoginSalvationSuccess(HttpResultBaseBean<List<SRCLoginSalvationBean>> bean);
        void onLoginSalvationFailure(String message);
        void onLoginOrganizationSuccess(HttpResultBaseBean<List<OrganizationBean>> bean);
        void onLoginOrganizationBeanFailure(String message);

        void onLoginPcodeSuccess(HttpResultBaseBean<List<PcodeDataBean>> bean);
        void onLoginPcodeBeanFailure(String message);
    }

    interface  presenter{

    }
}
