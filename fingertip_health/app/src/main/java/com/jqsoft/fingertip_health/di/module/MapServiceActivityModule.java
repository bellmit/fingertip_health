package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MapServiceActivityContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class MapServiceActivityModule {

    private MapServiceActivityContract.View view;

    public MapServiceActivityModule(MapServiceActivityContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MapServiceActivityContract.View providerView(){
        return view;
    }

}
