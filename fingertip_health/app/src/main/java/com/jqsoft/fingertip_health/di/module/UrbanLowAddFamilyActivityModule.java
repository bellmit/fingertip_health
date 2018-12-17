package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.UrbanAddFamilyContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class UrbanLowAddFamilyActivityModule {

    private UrbanAddFamilyContract.View view;

    public UrbanLowAddFamilyActivityModule(UrbanAddFamilyContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public UrbanAddFamilyContract.View providerView(){
        return view;
    }

}
