package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ReceptionActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = ReceptionActivityModule.class)
public interface ReceptionActivityComponent {
    void inject( ReceptionActivity ReceptionActivity);
}
