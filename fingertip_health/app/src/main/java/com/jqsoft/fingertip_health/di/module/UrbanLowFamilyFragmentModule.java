package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.UrbanLowFamilyFragmentContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class UrbanLowFamilyFragmentModule {

    private UrbanLowFamilyFragmentContract.View view;

    public UrbanLowFamilyFragmentModule(UrbanLowFamilyFragmentContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public UrbanLowFamilyFragmentContract.View providerView(){
        return view;
    }

}
