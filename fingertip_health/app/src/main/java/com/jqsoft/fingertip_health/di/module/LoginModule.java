package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.LoginContract;

import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class LoginModule {

    private LoginContract.View view;

    public LoginModule(LoginContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public LoginContract.View providerView(){
        return view;
    }

}