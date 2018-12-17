package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.CoreIndexContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class CoreIndexActivityModule {

    private CoreIndexContract.View view;

    public CoreIndexActivityModule(CoreIndexContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public CoreIndexContract.View providerView(){
        return view;
    }

}
