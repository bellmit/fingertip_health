package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceAccountTrendStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceAccountTrendStatisticsFragmentModule {

    private SubsistenceAccountTrendStatisticsFragmentContract.View view;

    public SubsistenceAccountTrendStatisticsFragmentModule(SubsistenceAccountTrendStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceAccountTrendStatisticsFragmentContract.View providerView(){
        return view;
    }

}
