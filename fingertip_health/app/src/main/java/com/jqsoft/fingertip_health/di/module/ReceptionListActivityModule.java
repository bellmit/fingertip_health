package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.ReceptionListActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class ReceptionListActivityModule {

    private ReceptionListActivityContract.View view;

    public ReceptionListActivityModule(ReceptionListActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public ReceptionListActivityContract.View providerView(){
        return view;
    }

}
