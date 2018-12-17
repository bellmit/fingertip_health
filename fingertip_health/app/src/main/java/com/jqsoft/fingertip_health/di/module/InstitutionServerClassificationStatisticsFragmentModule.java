package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.InstitutionServerClassificationStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class InstitutionServerClassificationStatisticsFragmentModule {

    private InstitutionServerClassificationStatisticsFragmentContract.View view;

    public InstitutionServerClassificationStatisticsFragmentModule(InstitutionServerClassificationStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public InstitutionServerClassificationStatisticsFragmentContract.View providerView(){
        return view;
    }

}
