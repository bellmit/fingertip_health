package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.AddFindContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class AddFindModule {

    private AddFindContract.View view;

    public AddFindModule(AddFindContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AddFindContract.View providerView(){
        return view;
    }

}