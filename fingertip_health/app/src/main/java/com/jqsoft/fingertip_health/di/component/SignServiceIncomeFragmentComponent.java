package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignServiceIncomeFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.SignServiceIncomeFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(modules = SignServiceIncomeFragmentModule.class)
public interface SignServiceIncomeFragmentComponent {
    void inject(SignServiceIncomeFragment signServiceIncomeFragment);
}
