package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.OnlineConsultationActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class OnlineConsultationActivityModule {

    private OnlineConsultationActivityContract.View view;

    public OnlineConsultationActivityModule(OnlineConsultationActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public OnlineConsultationActivityContract.View providerView(){
        return view;
    }

}
