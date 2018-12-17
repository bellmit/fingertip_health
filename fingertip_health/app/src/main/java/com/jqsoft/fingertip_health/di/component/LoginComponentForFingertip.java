package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.LoginModuleForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = LoginModuleForFingertip.class)
public interface LoginComponentForFingertip {
    void inject(LoginActivityNew loginActivity);
//    void inject(LoginActivity loginActivity);
}
