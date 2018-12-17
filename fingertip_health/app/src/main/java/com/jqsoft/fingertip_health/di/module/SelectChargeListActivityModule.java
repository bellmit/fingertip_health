package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.contract.SeletChargeListActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class SelectChargeListActivityModule {

    private SeletChargeListActivityContract.View view;

    public SelectChargeListActivityModule(SeletChargeListActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SeletChargeListActivityContract.View providerView(){
        return view;
    }

}
