package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.MedicalAssistantMoneyConstitutionStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class MedicalAssistantMoneyConstitutionStatisticsFragmentModule {

    private MedicalAssistantMoneyConstitutionStatisticsFragmentContract.View view;

    public MedicalAssistantMoneyConstitutionStatisticsFragmentModule(MedicalAssistantMoneyConstitutionStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public MedicalAssistantMoneyConstitutionStatisticsFragmentContract.View providerView(){
        return view;
    }

}
