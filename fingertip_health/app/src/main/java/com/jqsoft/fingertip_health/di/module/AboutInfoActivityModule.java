package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.AboutInfoContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class AboutInfoActivityModule {

    private AboutInfoContract.View view;

    public AboutInfoActivityModule(AboutInfoContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AboutInfoContract.View providerView(){
        return view;
    }

}
