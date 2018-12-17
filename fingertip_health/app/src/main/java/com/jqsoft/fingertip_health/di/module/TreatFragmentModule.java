package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.IndexFragmentContract;
import com.jqsoft.fingertip_health.di.contract.TreatFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2021.
 */

@Module
public class TreatFragmentModule {

    private TreatFragmentContract.View view;

    public TreatFragmentModule(TreatFragmentContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public TreatFragmentContract.View providerView() {
        return view;
    }

}