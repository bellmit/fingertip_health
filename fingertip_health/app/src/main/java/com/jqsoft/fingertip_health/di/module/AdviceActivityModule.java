package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.AdviceActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class AdviceActivityModule {

    private  AdviceActivityContract.View view;

    public AdviceActivityModule( AdviceActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public  AdviceActivityContract.View providerView(){
        return view;
    }

}
