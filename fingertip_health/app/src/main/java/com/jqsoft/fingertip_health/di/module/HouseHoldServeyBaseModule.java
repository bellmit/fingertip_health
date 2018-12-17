package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HouseHoldServeyBaseContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class HouseHoldServeyBaseModule {

    private HouseHoldServeyBaseContract.View view;

    public HouseHoldServeyBaseModule(HouseHoldServeyBaseContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HouseHoldServeyBaseContract.View providerView() {
        return view;
    }

}
