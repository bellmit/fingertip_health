package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.PolicyActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.PolicyActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = PolicyActivityModule.class)
public interface PolicyActivityComponent {
    void inject(PolicyActivity policyActivity);
}
