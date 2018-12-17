package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HandleProgressDetailActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.HandleProgressDetailActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = HandleProgressDetailActivityModule.class)
public interface HandleProgressDetailActivityComponent {
    void inject(HandleProgressDetailActivity handleProgressDetailActivity);
}
