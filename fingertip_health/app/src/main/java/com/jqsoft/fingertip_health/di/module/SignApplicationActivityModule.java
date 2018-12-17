package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignApplicationActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignApplicationActivityModule {

    private SignApplicationActivityContract.View view;

    public SignApplicationActivityModule(SignApplicationActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignApplicationActivityContract.View providerView(){
        return view;
    }

}
