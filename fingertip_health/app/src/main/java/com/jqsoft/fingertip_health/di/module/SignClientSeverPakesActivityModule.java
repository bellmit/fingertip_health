package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignClientSeverPakesContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignClientSeverPakesActivityModule {

    private SignClientSeverPakesContract.View view;

    public SignClientSeverPakesActivityModule(SignClientSeverPakesContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignClientSeverPakesContract.View providerView(){
        return view;
    }

}
