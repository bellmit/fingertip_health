package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignServiceAssessActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignServiceAssessActivityModule {

    private SignServiceAssessActivityContract.View view;

    public SignServiceAssessActivityModule(SignServiceAssessActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignServiceAssessActivityContract.View providerView(){
        return view;
    }

}
