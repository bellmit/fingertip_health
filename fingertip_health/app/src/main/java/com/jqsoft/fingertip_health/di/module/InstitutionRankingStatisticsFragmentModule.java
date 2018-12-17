package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.InstitutionRankingStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class InstitutionRankingStatisticsFragmentModule {

    private InstitutionRankingStatisticsFragmentContract.View view;

    public InstitutionRankingStatisticsFragmentModule(InstitutionRankingStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public InstitutionRankingStatisticsFragmentContract.View providerView(){
        return view;
    }

}
