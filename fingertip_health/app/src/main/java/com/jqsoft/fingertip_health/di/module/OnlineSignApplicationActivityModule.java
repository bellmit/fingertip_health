package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.OnLineSignApplicationContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class OnlineSignApplicationActivityModule {

    private OnLineSignApplicationContract.View view;

    public OnlineSignApplicationActivityModule(OnLineSignApplicationContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public OnLineSignApplicationContract.View providerView() {
        return view;
    }

}
