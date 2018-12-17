package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.IndexFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class IndexFragmentModule {

    private IndexFragmentContract.View view;

    public IndexFragmentModule(IndexFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public IndexFragmentContract.View providerView(){
        return view;
    }

}