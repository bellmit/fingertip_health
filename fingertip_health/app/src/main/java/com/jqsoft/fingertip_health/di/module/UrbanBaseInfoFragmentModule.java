package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.UrbanBaseInfoContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class UrbanBaseInfoFragmentModule {

    private UrbanBaseInfoContract.View view;

    public UrbanBaseInfoFragmentModule(UrbanBaseInfoContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public UrbanBaseInfoContract.View providerView(){
        return view;
    }

}
