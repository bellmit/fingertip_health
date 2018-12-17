package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.PersonCollectionActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class PersonCollectionActivityModule {

    private PersonCollectionActivityContract.View view;

    public PersonCollectionActivityModule(PersonCollectionActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public PersonCollectionActivityContract.View providerView(){
        return view;
    }

}
