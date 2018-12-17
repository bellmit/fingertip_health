package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignServiceIncomeActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignServiceIncomeActivityModule {

    private SignServiceIncomeActivityContract.View view;

    public SignServiceIncomeActivityModule(SignServiceIncomeActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignServiceIncomeActivityContract.View providerView(){
        return view;
    }

}
