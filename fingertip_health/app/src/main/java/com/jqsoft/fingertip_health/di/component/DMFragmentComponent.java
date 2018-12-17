package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.DMFragmentModule;
import com.jqsoft.fingertip_health.di.module.HighBloodFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.DMFragment3;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment3;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(modules = DMFragmentModule.class)
public interface DMFragmentComponent {
    void inject(DMFragment3 dmFragment3);
}
