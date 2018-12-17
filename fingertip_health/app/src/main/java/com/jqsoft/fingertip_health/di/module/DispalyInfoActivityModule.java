package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.DispalyBaseinfoContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2017/12/27.
 */

@Module
public class DispalyInfoActivityModule {

    private DispalyBaseinfoContract.View view;

    public DispalyInfoActivityModule(DispalyBaseinfoContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public DispalyBaseinfoContract.View providerView(){
        return view;
    }

}
