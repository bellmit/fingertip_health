package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.ReservationContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class ReservationActivityModule {

    private ReservationContract.View view;

    public ReservationActivityModule(ReservationContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public ReservationContract.View providerView(){
        return view;
    }

}
