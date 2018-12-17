package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.ReserverContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class ReserverActivityModule {

    private ReserverContract.View view;

    public ReserverActivityModule(ReserverContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public ReserverContract.View providerView(){
        return view;
    }

}
