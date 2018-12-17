package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.HouseFileBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public interface HouseHoldFlieContract {
    interface View {

        void onLoadListSuccess(GCAHttpResultBaseBean<List<HouseFileBean>> bean);

        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<HouseFileBean>> bean);

        void onLoadListFailure(String message);


    }

    interface presenter {

    }
}
