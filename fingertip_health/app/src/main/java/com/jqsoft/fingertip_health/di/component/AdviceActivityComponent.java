package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AdviceActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.AdviceActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules =  AdviceActivityModule.class)
public interface AdviceActivityComponent {
    void inject( AdviceActivity adviceActivity);
}
