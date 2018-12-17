package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.OnLineChatingActivityContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class OnlineChatingActivityModule {

    private OnLineChatingActivityContract.View view;

    public OnlineChatingActivityModule(OnLineChatingActivityContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public OnLineChatingActivityContract.View providerView(){
        return view;
    }

}