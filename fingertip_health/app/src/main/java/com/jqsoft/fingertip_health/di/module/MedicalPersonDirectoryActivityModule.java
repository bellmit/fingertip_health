package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MedicalPersonDirectoryActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class MedicalPersonDirectoryActivityModule {

    private MedicalPersonDirectoryActivityContract.View view;

    public MedicalPersonDirectoryActivityModule(MedicalPersonDirectoryActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MedicalPersonDirectoryActivityContract.View providerView(){
        return view;
    }

}
