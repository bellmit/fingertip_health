package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.contract.PersonJiandangListActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class PersonJiandangListActivityModule {

    private PersonJiandangListActivityContract.View view;

    public PersonJiandangListActivityModule(PersonJiandangListActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public PersonJiandangListActivityContract.View providerView(){
        return view;
    }

}
