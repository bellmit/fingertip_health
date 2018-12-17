package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.GuideActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.GuideActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = GuideActivityModule.class)
public interface GuideActivityComponent {
    void inject(GuideActivity GuideActivity);
}
