package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HandleProgressModule;
import com.jqsoft.fingertip_health.di.ui.activity.HandleProgress;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = HandleProgressModule.class)
public interface HandleProgressComponent {
    void inject(HandleProgress handleProgress);
}
