package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.RegisterContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class RegisterModule {

    private RegisterContract.View view;

    public RegisterModule(RegisterContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public RegisterContract.View providerView(){
        return view;
    }

}