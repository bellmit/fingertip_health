package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceArchiveAgeClassificationStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceArchiveAgeClassificationStatisticsFragmentModule {

    private SubsistenceArchiveAgeClassificationStatisticsFragmentContract.View view;

    public SubsistenceArchiveAgeClassificationStatisticsFragmentModule(SubsistenceArchiveAgeClassificationStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceArchiveAgeClassificationStatisticsFragmentContract.View providerView(){
        return view;
    }

}
