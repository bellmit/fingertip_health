package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.DisPlayDemocraticModule;
import com.jqsoft.fingertip_health.di.ui.activity.DisPlayDemocraticActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = DisPlayDemocraticModule.class)
public interface DisPlayDemocraticComponent {
    void inject(DisPlayDemocraticActivity disPlayDemocraticActivity);
}
