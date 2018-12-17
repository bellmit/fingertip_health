package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuideBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuidePostBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface IgGuideActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<IgGuideBean>> bean);
        void onLoadListFailure(String message, boolean isLoadMore);
        void onPostSuccess(GCAHttpResultBaseBean<List<IgGuidePostBean>> bean);
        void onPostFailure(String message);

    }

    interface  presenter{

    }
}
