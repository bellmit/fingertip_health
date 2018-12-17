package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HandleProgressContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2017/12/27.
 */

@Module
public class HandleProgressModule {

    private HandleProgressContract.View view;

    public HandleProgressModule(HandleProgressContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HandleProgressContract.View providerView() {
        return view;
    }

}
