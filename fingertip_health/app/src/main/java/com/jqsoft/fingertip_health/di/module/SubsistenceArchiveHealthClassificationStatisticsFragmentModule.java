package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceArchiveHealthClassificationStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceArchiveHealthClassificationStatisticsFragmentModule {

    private SubsistenceArchiveHealthClassificationStatisticsFragmentContract.View view;

    public SubsistenceArchiveHealthClassificationStatisticsFragmentModule(SubsistenceArchiveHealthClassificationStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceArchiveHealthClassificationStatisticsFragmentContract.View providerView(){
        return view;
    }

}
