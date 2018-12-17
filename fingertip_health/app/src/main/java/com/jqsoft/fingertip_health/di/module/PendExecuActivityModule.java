package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.PendExecuContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class PendExecuActivityModule {

    private PendExecuContract.View view;

    public PendExecuActivityModule(PendExecuContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public PendExecuContract.View providerView(){
        return view;
    }

}
