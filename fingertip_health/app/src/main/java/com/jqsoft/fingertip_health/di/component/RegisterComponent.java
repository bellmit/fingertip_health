package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.RegisterModule;
import com.jqsoft.fingertip_health.di.ui.activity.RegisterActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = RegisterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity registerActivity);
}
