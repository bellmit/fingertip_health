package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HouseHoldFlieContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class HouseHoldFileModule {

    private HouseHoldFlieContract.View view;

    public HouseHoldFileModule(HouseHoldFlieContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HouseHoldFlieContract.View providerView() {
        return view;
    }

}
