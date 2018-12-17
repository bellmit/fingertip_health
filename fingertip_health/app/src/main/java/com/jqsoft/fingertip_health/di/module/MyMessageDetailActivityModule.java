package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MyMessageDetailActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class MyMessageDetailActivityModule {

    private MyMessageDetailActivityContract.View view;

    public MyMessageDetailActivityModule(MyMessageDetailActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MyMessageDetailActivityContract.View providerView(){
        return view;
    }

}
