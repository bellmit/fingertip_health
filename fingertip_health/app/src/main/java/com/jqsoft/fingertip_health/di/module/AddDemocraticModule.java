package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.AddDemoCraticContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2017/12/27.
 */

@Module
public class AddDemocraticModule {

    private AddDemoCraticContract.View view;

    public AddDemocraticModule(AddDemoCraticContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AddDemoCraticContract.View providerView(){
        return view;
    }

}
