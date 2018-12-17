package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.SignServiceAssessActivityModule;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceAssessActivity;
import com.jqsoft.fingertip_health.di_app.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@ActivityScope
@Subcomponent(modules = SignServiceAssessActivityModule.class)
public interface SignServiceAssessActivityComponent {
    void inject(SignServiceAssessActivity signServiceAssessActivity);
}
