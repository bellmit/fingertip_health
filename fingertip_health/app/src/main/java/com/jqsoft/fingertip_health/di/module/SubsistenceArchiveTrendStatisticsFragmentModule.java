package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceArchiveTrendStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceArchiveTrendStatisticsFragmentModule {

    private SubsistenceArchiveTrendStatisticsFragmentContract.View view;

    public SubsistenceArchiveTrendStatisticsFragmentModule(SubsistenceArchiveTrendStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceArchiveTrendStatisticsFragmentContract.View providerView(){
        return view;
    }

}
