package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SocialHistoryActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.SocialHistoryActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = SocialHistoryActivityModule.class)
public interface SocialHistoryActivityComponent {
    void inject(SocialHistoryActivity socialHistoryActivity);
}
