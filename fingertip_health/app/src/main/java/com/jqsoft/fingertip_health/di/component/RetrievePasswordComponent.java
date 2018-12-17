package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.RetrievePasswordModule;
import com.jqsoft.fingertip_health.di.ui.activity.RetrievePasswordActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = RetrievePasswordModule.class)
public interface RetrievePasswordComponent {
    void inject(RetrievePasswordActivity retrievePasswordActivity);
}
