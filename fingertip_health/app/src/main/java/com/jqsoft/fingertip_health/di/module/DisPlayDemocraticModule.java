package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.DisPlayDemoCraticContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2017/12/27.
 */

@Module
public class DisPlayDemocraticModule {

    private DisPlayDemoCraticContract.View view;

    public DisPlayDemocraticModule(DisPlayDemoCraticContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public DisPlayDemoCraticContract.View providerView(){
        return view;
    }

}
