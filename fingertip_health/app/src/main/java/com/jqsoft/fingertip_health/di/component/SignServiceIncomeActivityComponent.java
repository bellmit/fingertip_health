package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignServiceIncomeActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceIncomeActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = SignServiceIncomeActivityModule.class)
public interface SignServiceIncomeActivityComponent {
    void inject(SignServiceIncomeActivity signServiceIncomeActivity);
}
