package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MyInfoContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class MyInfoModule {

    private MyInfoContract.View view;

    public MyInfoModule(MyInfoContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MyInfoContract.View providerView(){
        return view;
    }

}