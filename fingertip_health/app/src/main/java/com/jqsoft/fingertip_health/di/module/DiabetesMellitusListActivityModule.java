package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.DiabetesMellitusListActivityContract;
import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class DiabetesMellitusListActivityModule {

    private DiabetesMellitusListActivityContract.View view;

    public DiabetesMellitusListActivityModule(DiabetesMellitusListActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public DiabetesMellitusListActivityContract.View providerView(){
        return view;
    }

}
