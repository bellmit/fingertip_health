package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator 2017/5/21.
 */

public interface HarbelFragmentContract {
    interface View {
        void onHarbelSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onHarbelFailure(String msg);
    }

    interface presenter {

    }
}
