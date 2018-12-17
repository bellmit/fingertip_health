package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.contract.SignDoctorListActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class SignDoctorListActivityModule {

    private SignDoctorListActivityContract.View view;

    public SignDoctorListActivityModule(SignDoctorListActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignDoctorListActivityContract.View providerView(){
        return view;
    }

}
