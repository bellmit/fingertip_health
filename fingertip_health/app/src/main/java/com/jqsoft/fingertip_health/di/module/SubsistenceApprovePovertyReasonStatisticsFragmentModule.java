package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.SubsistenceApprovePovertyReasonStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class SubsistenceApprovePovertyReasonStatisticsFragmentModule {

    private SubsistenceApprovePovertyReasonStatisticsFragmentContract.View view;

    public SubsistenceApprovePovertyReasonStatisticsFragmentModule(SubsistenceApprovePovertyReasonStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public SubsistenceApprovePovertyReasonStatisticsFragmentContract.View providerView(){
        return view;
    }

}
