package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SocialDetailActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.SocialDetailActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = SocialDetailActivityModule.class)
public interface SocialDetailActivityComponent {
    void inject(SocialDetailActivity socialDetailActivity);
}
