package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.DemocraticAppraisalContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class DemocraticAppraisalModule {

    private DemocraticAppraisalContract.View view;

    public DemocraticAppraisalModule(DemocraticAppraisalContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public DemocraticAppraisalContract.View providerView() {
        return view;
    }

}
