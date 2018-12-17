package com.jqsoft.fingertip_health.di.contract;

import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

/**
 * Created by Administrator 2017/5/21.
 */

public interface RecipeFragmentContract {
    interface View {
        void onRecipeSuccess(HttpResultBaseBeanForFingertip<String> bean);
        void onRecipeFailure(String msg);
    }

    interface presenter {

    }
}
