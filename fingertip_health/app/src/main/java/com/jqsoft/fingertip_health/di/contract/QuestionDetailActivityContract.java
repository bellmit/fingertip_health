package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.QuestionDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface QuestionDetailActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<QuestionDetailBean> bean);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<QuestionDetailBean> bean);
        void onLoadListFailure(String message);


    }

    interface  presenter{

    }
}
