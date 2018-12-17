package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.ExecutionProjectsActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class ExecutionProjectsActivityModule {

    private ExecutionProjectsActivityContract.View view;

    public ExecutionProjectsActivityModule(ExecutionProjectsActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public ExecutionProjectsActivityContract.View providerView(){
        return view;
    }

}
