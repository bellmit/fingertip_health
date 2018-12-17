package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.PolityActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class PolityActivityModule {

    private PolityActivityContract.View view;

    public PolityActivityModule(PolityActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public PolityActivityContract.View providerView(){
        return view;
    }

}
