package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.ModuleListFragmentContract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;


@Module
public class ModuleListFragmentModule {

    private ModuleListFragmentContract.View view;

    public ModuleListFragmentModule(ModuleListFragmentContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public ModuleListFragmentContract.View providerView() {
        return view;
    }

}
