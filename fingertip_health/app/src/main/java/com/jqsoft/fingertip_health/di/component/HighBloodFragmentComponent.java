package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HighBloodFragmentModule;
import com.jqsoft.fingertip_health.di.module.PeopleSignInfoFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment3;
import com.jqsoft.fingertip_health.di.ui.fragment.PeopleSignFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(modules = HighBloodFragmentModule.class)
public interface HighBloodFragmentComponent {
    void inject(HBFragment3 hbFragment3);
}
