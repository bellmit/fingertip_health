package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ModuleListFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.QueryDataFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(modules = ModuleListFragmentModule.class)
public interface ModuleListFragmentComponent {
    void inject(QueryDataFragment queryDataFragment);
}
