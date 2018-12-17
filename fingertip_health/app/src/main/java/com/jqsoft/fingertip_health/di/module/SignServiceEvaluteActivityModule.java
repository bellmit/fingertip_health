package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SignServiceEvaluteContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SignServiceEvaluteActivityModule {

    private SignServiceEvaluteContract.View view;

    public SignServiceEvaluteActivityModule(SignServiceEvaluteContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SignServiceEvaluteContract.View providerView() {
        return view;
    }

}
