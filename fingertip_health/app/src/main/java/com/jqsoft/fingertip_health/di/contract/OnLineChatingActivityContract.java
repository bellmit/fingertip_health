package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.PersonMessage;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface OnLineChatingActivityContract {
    interface View {

        void onMessageDataSuccess(HttpResultBaseBean<List<PersonMessage>> bean);

        void onMessageDataFailure(String message, boolean isLoadMore);
        void  onLoadMoreListSuccess(HttpResultBaseBean<List<PersonMessage>> bean);

        void onsaveOnlineFailure(String message);

        void onsaveOnlineSuccess(HttpResultBaseBean<LoginResultBean2> bean);

        void updateOnlineSuccess(HttpResultBaseBean<LoginResultBean2> bean);

        void updateOnlineFailure(String message);

    }

    interface presenter {

    }
}
