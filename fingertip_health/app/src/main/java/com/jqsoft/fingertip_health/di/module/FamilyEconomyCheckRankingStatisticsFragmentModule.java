package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.FamilyEconomyCheckRankingStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class FamilyEconomyCheckRankingStatisticsFragmentModule {

    private FamilyEconomyCheckRankingStatisticsFragmentContract.View view;

    public FamilyEconomyCheckRankingStatisticsFragmentModule(FamilyEconomyCheckRankingStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public FamilyEconomyCheckRankingStatisticsFragmentContract.View providerView(){
        return view;
    }

}
