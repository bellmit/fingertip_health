package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.AddServeryActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jerry on 2017/12/27.
 */

@Module
public class AddServeryActivityModule {

    private AddServeryActivityContract.View view;

    public AddServeryActivityModule(AddServeryActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AddServeryActivityContract.View providerView(){
        return view;
    }

}
