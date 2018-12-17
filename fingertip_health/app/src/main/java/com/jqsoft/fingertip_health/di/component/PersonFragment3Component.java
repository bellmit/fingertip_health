package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HighBloodFragmentModule;
import com.jqsoft.fingertip_health.di.module.PersonFragment3Module;
import com.jqsoft.fingertip_health.di.ui.fragment.HBFragment3;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.PersonFragment3;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(modules = PersonFragment3Module.class)
public interface PersonFragment3Component {
    void inject(PersonFragment3 hbFragment3);
}
