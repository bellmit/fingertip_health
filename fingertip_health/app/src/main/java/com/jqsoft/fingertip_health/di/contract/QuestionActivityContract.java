package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.QuestionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;



public interface QuestionActivityContract {
    interface View{

        void onLoadListSuccess(GCAHttpResultBaseBean<List<QuestionBean>> bean);
        void onLoadListFailure(String message);
        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<QuestionBean>> bean);
        void onLoadMoreListfail(String message);

    }

    interface  presenter{

    }
}
