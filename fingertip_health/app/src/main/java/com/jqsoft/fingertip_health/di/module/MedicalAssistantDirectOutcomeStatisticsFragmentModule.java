package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MedicalAssistantDirectOutcomeStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class MedicalAssistantDirectOutcomeStatisticsFragmentModule {

    private MedicalAssistantDirectOutcomeStatisticsFragmentContract.View view;

    public MedicalAssistantDirectOutcomeStatisticsFragmentModule(MedicalAssistantDirectOutcomeStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public MedicalAssistantDirectOutcomeStatisticsFragmentContract.View providerView(){
        return view;
    }

}
