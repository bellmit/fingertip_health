package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HouseHoldServeryContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class HouseHoldserveryModule {

    private HouseHoldServeryContract.View view;

    public HouseHoldserveryModule(HouseHoldServeryContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HouseHoldServeryContract.View providerView() {
        return view;
    }

}
