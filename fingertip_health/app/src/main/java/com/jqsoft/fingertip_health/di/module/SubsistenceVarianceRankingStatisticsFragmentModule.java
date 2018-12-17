package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceVarianceRankingStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceVarianceRankingStatisticsFragmentModule {

    private SubsistenceVarianceRankingStatisticsFragmentContract.View view;

    public SubsistenceVarianceRankingStatisticsFragmentModule(SubsistenceVarianceRankingStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceVarianceRankingStatisticsFragmentContract.View providerView(){
        return view;
    }

}
