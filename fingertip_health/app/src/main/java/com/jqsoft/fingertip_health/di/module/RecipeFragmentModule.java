package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.RecipeFragmentContract;
import com.jqsoft.fingertip_health.di.contract.TreatFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2021.
 */

@Module
public class RecipeFragmentModule {

    private RecipeFragmentContract.View view;

    public RecipeFragmentModule(RecipeFragmentContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public RecipeFragmentContract.View providerView() {
        return view;
    }

}