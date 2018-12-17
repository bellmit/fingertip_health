package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.UseCollectionFragmentContract;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class UseCollectionFragmentModule {

    private UseCollectionFragmentContract.View view;

    public UseCollectionFragmentModule(UseCollectionFragmentContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public UseCollectionFragmentContract.View providerView(){
        return view;
    }

}
