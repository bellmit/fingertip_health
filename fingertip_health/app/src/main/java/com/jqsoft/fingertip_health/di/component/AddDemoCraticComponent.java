package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.AddDemocraticModule;
import com.jqsoft.fingertip_health.di.ui.activity.AddDemocraticActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = AddDemocraticModule.class)
public interface AddDemoCraticComponent {
    void inject(AddDemocraticActivity addDemocraticActivity);
}
