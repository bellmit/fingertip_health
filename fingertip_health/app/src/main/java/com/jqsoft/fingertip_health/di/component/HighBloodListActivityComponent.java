package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.module.SocialAssistanceObjectActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SocialAssistanceObject;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = HighBloodListActivityModule.class)
public interface HighBloodListActivityComponent {
    void inject(HighBloodListActivity highBloodListActivity);
}
