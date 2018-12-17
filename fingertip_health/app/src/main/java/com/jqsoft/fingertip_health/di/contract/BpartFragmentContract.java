package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.PersonInfoList;
import com.jqsoft.fingertip_health.bean.PersonnelInfoData;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface BpartFragmentContract {
    interface View{
        void onLoadListSuccess(HttpResultBaseBean<List<PersonInfoList>> bean);
        void onLoginFailure(String message);
        void onPersonnelInfo(HttpResultBaseBean<PersonnelInfoData> bean);
    }

    interface  presenter{

    }
}
