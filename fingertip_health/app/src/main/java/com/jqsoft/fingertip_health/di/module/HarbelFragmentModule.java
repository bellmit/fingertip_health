package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HarbelFragmentContract;
import com.jqsoft.fingertip_health.di.contract.RecipeFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2021.
 */

@Module
public class HarbelFragmentModule {

    private HarbelFragmentContract.View view;

    public HarbelFragmentModule(HarbelFragmentContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public HarbelFragmentContract.View providerView() {
        return view;
    }

}