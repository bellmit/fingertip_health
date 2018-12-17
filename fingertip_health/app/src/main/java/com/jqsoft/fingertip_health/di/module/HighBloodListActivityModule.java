package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.contract.SocialAssistanceObjectActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class HighBloodListActivityModule {

    private HighBloodListActivityContract.View view;

    public HighBloodListActivityModule(HighBloodListActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public HighBloodListActivityContract.View providerView(){
        return view;
    }

}
