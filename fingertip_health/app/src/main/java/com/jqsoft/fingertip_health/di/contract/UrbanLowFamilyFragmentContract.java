package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFamilyBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianSaveBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface UrbanLowFamilyFragmentContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<UrbanLowFamilyBean>> bean);
        void onLoadListFailure(String message);

        void onLoadFujianSuccess(GCAHttpResultBaseBean<List<UrbanLowFujianBean>> bean);
        void onLoadFujianListFailure(String message);

        void onLoadFujianTakeSuccess(GCAHttpResultBaseBean<UrbanLowFujianSaveBean> bean);
        void onLoadFujianTakeListFailure(String message);

        //家庭成员删除
        void onLoadFamilydeleteSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean);
        void onLoadFamilydeleteListFailure(String message);

        //附件删除
        void onLoadFujiandeleteSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean);
        void onLoadFujiandeleteListFailure(String message);

        //附件编辑
        void onLoadFujianBIanjiSuccess(GCAHttpResultBaseBean<HttpResultEmptyBean> bean);
        void onLoadFujianBIanjiListFailure(String message);


    }

    interface  presenter{

    }
}
