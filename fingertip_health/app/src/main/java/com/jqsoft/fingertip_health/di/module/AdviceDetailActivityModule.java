package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.AdviceDetailActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;



@Module
public class AdviceDetailActivityModule {

    private AdviceDetailActivityContract.View view;

    public AdviceDetailActivityModule(AdviceDetailActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AdviceDetailActivityContract.View providerView(){
        return view;
    }

}
