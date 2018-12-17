package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.ChangePasswordActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ChangePasswordActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;



@ActivityScope
@Subcomponent(modules = ChangePasswordActivityModule.class)
public interface ChangePasswordActivityComponent {
    void inject(ChangePasswordActivity ChangePasswordActivity);
}
