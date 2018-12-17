package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceAccountIncreaseRatioStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceAccountIncreaseRatioStatisticsFragmentModule {

    private SubsistenceAccountIncreaseRatioStatisticsFragmentContract.View view;

    public SubsistenceAccountIncreaseRatioStatisticsFragmentModule(SubsistenceAccountIncreaseRatioStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceAccountIncreaseRatioStatisticsFragmentContract.View providerView(){
        return view;
    }

}
