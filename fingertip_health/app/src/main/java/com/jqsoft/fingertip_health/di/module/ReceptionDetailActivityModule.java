package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.ReceptionDetailActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class ReceptionDetailActivityModule {

    private ReceptionDetailActivityContract.View view;

    public ReceptionDetailActivityModule(ReceptionDetailActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public ReceptionDetailActivityContract.View providerView(){
        return view;
    }

}
