package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.NotificationActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.NotificationActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = NotificationActivityModule.class)
public interface NotificationActivityComponent {
    void inject(NotificationActivity policyActivity);
}
