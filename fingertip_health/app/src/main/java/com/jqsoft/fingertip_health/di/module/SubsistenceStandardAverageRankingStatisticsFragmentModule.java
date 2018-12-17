package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceStandardAverageRankingStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceStandardAverageRankingStatisticsFragmentModule {

    private SubsistenceStandardAverageRankingStatisticsFragmentContract.View view;

    public SubsistenceStandardAverageRankingStatisticsFragmentModule(SubsistenceStandardAverageRankingStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceStandardAverageRankingStatisticsFragmentContract.View providerView(){
        return view;
    }

}
