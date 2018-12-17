package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceApproveTrendStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceApproveTrendStatisticsFragmentModule {

    private SubsistenceApproveTrendStatisticsFragmentContract.View view;

    public SubsistenceApproveTrendStatisticsFragmentModule(SubsistenceApproveTrendStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceApproveTrendStatisticsFragmentContract.View providerView(){
        return view;
    }

}
