package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.LoginContractForFingertip;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class LoginModuleForFingertip {

    private LoginContractForFingertip.View view;

    public LoginModuleForFingertip(LoginContractForFingertip.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public LoginContractForFingertip.View providerView(){
        return view;
    }

}