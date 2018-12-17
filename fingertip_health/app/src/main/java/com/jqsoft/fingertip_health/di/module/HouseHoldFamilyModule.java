package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HouseHoldFaimilyMemberContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class HouseHoldFamilyModule {

    private HouseHoldFaimilyMemberContract.View view;

    public HouseHoldFamilyModule(HouseHoldFaimilyMemberContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HouseHoldFaimilyMemberContract.View providerView() {
        return view;
    }

}
