package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.RetrievePasswordContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class RetrievePasswordModule {

    private RetrievePasswordContract.View view;

    public RetrievePasswordModule(RetrievePasswordContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public RetrievePasswordContract.View providerView(){
        return view;
    }

}