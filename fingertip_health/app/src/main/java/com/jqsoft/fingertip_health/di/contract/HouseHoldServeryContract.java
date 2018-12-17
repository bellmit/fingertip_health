package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.HouseHoldSeveryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */

public interface HouseHoldServeryContract {
    interface View {

        void onLoadListSuccess(GCAHttpResultBaseBean<List<HouseHoldSeveryBean>> bean);

        void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<HouseHoldSeveryBean>> bean);

        void onLoadListFailure(String message);


    }

    interface presenter {

    }
}
