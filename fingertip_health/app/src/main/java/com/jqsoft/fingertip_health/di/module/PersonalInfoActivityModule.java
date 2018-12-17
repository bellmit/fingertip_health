package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.PersonalInfoContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class PersonalInfoActivityModule {

    private PersonalInfoContract.View view;

    public PersonalInfoActivityModule(PersonalInfoContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public PersonalInfoContract.View providerView(){
        return view;
    }

}
