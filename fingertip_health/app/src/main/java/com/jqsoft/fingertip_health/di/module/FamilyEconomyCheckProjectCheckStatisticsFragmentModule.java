package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.FamilyEconomyCheckProjectCheckStatisticsFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class FamilyEconomyCheckProjectCheckStatisticsFragmentModule {

    private FamilyEconomyCheckProjectCheckStatisticsFragmentContract.View view;

    public FamilyEconomyCheckProjectCheckStatisticsFragmentModule(FamilyEconomyCheckProjectCheckStatisticsFragmentContract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public FamilyEconomyCheckProjectCheckStatisticsFragmentContract.View providerView(){
        return view;
    }

}
