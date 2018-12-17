package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.LoginModule;
import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivityNew loginActivity);
//    void inject(LoginActivity loginActivity);
}
