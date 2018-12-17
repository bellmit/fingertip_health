package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.urbanLowInsActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class UrbanLowInsActivityModule {

    private urbanLowInsActivityContract.View view;

    public UrbanLowInsActivityModule(urbanLowInsActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public urbanLowInsActivityContract.View providerView(){
        return view;
    }

}
