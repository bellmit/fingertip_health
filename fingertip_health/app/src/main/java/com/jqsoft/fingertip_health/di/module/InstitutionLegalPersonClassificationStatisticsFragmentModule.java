package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.InstitutionLegalPersonClassificationStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class InstitutionLegalPersonClassificationStatisticsFragmentModule {

    private InstitutionLegalPersonClassificationStatisticsFragmentContract.View view;

    public InstitutionLegalPersonClassificationStatisticsFragmentModule(InstitutionLegalPersonClassificationStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public InstitutionLegalPersonClassificationStatisticsFragmentContract.View providerView(){
        return view;
    }

}
