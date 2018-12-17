package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignClientServiceAssessActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.ClientSignServiceAssessActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = SignClientServiceAssessActivityModule.class)
public interface SignClientServiceAssessActivityComponent {
    void inject(ClientSignServiceAssessActivity clientSignServiceAssessActivity);
}
