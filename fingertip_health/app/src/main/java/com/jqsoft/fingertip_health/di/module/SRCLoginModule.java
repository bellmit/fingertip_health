package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SRCLoginContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SRCLoginModule {

    private SRCLoginContract.View view;

    public SRCLoginModule(SRCLoginContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SRCLoginContract.View providerView(){
        return view;
    }

}