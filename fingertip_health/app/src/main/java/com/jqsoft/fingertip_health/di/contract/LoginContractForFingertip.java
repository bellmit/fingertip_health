package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface LoginContractForFingertip {
    interface View{

        void onLoginSuccess(HttpResultBaseBeanForFingertip<String> bean);

        void onLoginFailure(String message);

//        void onLoginAreaSuccess(HttpResultBaseBean<List<gdws_sys_area>> bean);
//        void onLoginAreaFailure(String message);
//
//        void onLoginDataDictionatySuccess(HttpResultBaseBean<List<SRCLoginDataDictionaryBean>> bean);
//        void onLoginDataDictionatyFailure(String message);
//
//        void onLoginSalvationSuccess(HttpResultBaseBean<List<SRCLoginSalvationBean>> bean);
//        void onLoginSalvationFailure(String message);
//        void onLoginOrganizationSuccess(HttpResultBaseBean<List<OrganizationBean>> bean);
//        void onLoginOrganizationBeanFailure(String message);
//
//        void onLoginPcodeSuccess(HttpResultBaseBean<List<PcodeDataBean>> bean);
//        void onLoginPcodeBeanFailure(String message);
    }

    interface  presenter{

    }
}
